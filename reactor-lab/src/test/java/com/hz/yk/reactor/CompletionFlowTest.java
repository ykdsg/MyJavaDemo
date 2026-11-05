package com.hz.yk.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletionFlowTest {

    private static final String INITIALIZED = "initialized";
    private static final String AGENT_STARTED = "agent-started";
    private static final String SAVED = "saved";
    private static final String FIRST_CHUNK = "hello";
    private static final String SECOND_CHUNK = " reactor";
    private static final String TOOL_STARTED = "tool-started";
    private static final String TOOL_REPLY = "tool-result";
    private static final String FINAL_ANSWER = "hello reactor";
    private static final String FACTORY_CALLED = "factory-called";
    private static final String TASK_EXECUTED = "task-executed";
    private static final String INITIALIZATION_FAILURE = "initialization failed";
    private static final List<String> CHUNK_LIST = List.of(FIRST_CHUNK, SECOND_CHUNK);
    private static final int NO_CALLS = 0;
    private static final int EVENT_CAPACITY = 4;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testVoidTaskCompletesWithoutCallingFlatMapMapper() {
        AtomicBoolean saved = new AtomicBoolean();
        AtomicInteger mappingCallCount = new AtomicInteger();
        Mono<Void> saveTask = Mono.fromRunnable(() -> saved.set(true));
        Mono<String> result = saveTask.flatMap(ignored -> {
            mappingCallCount.incrementAndGet();
            return Mono.just(FINAL_ANSWER);
        });

        StepVerifier.create(result).expectComplete().verify(VERIFY_TIMEOUT);

        assertTrue(saved.get());
        assertEquals(NO_CALLS, mappingCallCount.get());
    }

    @Test
    public void testThenStartsAgentAfterInitializationCompletes() {
        List<String> eventList = new ArrayList<>(EVENT_CAPACITY);
        Mono<Void> initialize = Mono.fromRunnable(() -> eventList.add(INITIALIZED));
        Mono<String> agent = Mono.fromSupplier(() -> {
            eventList.add(AGENT_STARTED);
            return FINAL_ANSWER;
        });
        Mono<String> result = initialize.then(agent);

        assertTrue(eventList.isEmpty());

        StepVerifier.create(result).expectNext(FINAL_ANSWER).expectComplete().verify(VERIFY_TIMEOUT);

        assertEquals(List.of(INITIALIZED, AGENT_STARTED), eventList);
    }

    @Test
    public void testThenReturnPreservesAnswerAfterSavingCompletes() {
        List<String> eventList = new ArrayList<>(EVENT_CAPACITY);
        Mono<String> result = Mono.just(FINAL_ANSWER)
                .flatMap(answer -> Mono.fromRunnable(() -> eventList.add(SAVED)).thenReturn(answer));

        assertTrue(eventList.isEmpty());

        StepVerifier.create(result).assertNext(answer -> {
            assertEquals(FINAL_ANSWER, answer);
            assertEquals(List.of(SAVED), eventList);
        }).expectComplete().verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testThenManyAndConcatWithRunToolsAfterAllChunks() {
        List<String> eventList = new ArrayList<>(EVENT_CAPACITY);
        List<String> receivedChunkList = new ArrayList<>(EVENT_CAPACITY);
        Mono<Void> initialize = Mono.fromRunnable(() -> eventList.add(INITIALIZED));
        Flux<String> llmStream = Flux.fromIterable(CHUNK_LIST).doOnNext(chunk -> {
            receivedChunkList.add(chunk);
            eventList.add(chunk);
        });
        Flux<String> result = initialize.thenMany(llmStream).concatWith(Flux.defer(() -> {
            assertEquals(CHUNK_LIST, receivedChunkList);
            eventList.add(TOOL_STARTED);
            return Flux.just(TOOL_REPLY);
        }));

        assertTrue(eventList.isEmpty());

        StepVerifier.create(result).expectNext(FIRST_CHUNK, SECOND_CHUNK, TOOL_REPLY).expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(List.of(INITIALIZED, FIRST_CHUNK, SECOND_CHUNK, TOOL_STARTED), eventList);
    }

    @Test
    public void testInitializationErrorPreventsAgentSubscription() {
        PublisherProbe<String> agent = PublisherProbe.of(Mono.just(FINAL_ANSWER));
        Mono<Void> initialize = Mono.error(new IllegalStateException(INITIALIZATION_FAILURE));
        Mono<String> result = initialize.then(agent.mono());

        StepVerifier.create(result).expectErrorMatches(
                        error -> error instanceof IllegalStateException && INITIALIZATION_FAILURE.equals(error.getMessage()))
                .verify(VERIFY_TIMEOUT);

        agent.assertWasNotSubscribed();
    }

    @Test
    public void testThenDelaysSubscriptionButDeferAlsoDelaysFactoryCall() {
        List<String> eventList = new ArrayList<>(EVENT_CAPACITY);
        Mono<Void> initialize = Mono.fromRunnable(() -> eventList.add(INITIALIZED));
        Supplier<Mono<String>> taskFactory = () -> {
            eventList.add(FACTORY_CALLED);
            return Mono.fromSupplier(() -> {
                eventList.add(TASK_EXECUTED);
                return FINAL_ANSWER;
            });
        };
        Mono<String> eagerFactory = initialize.then(taskFactory.get());

        assertEquals(List.of(FACTORY_CALLED), eventList);

        StepVerifier.create(eagerFactory).expectNext(FINAL_ANSWER).expectComplete().verify(VERIFY_TIMEOUT);

        assertEquals(List.of(FACTORY_CALLED, INITIALIZED, TASK_EXECUTED), eventList);
        eventList.clear();

        Mono<String> deferredFactory = initialize.then(Mono.defer(taskFactory));

        assertTrue(eventList.isEmpty());

        StepVerifier.create(deferredFactory).expectNext(FINAL_ANSWER).expectComplete().verify(VERIFY_TIMEOUT);

        assertEquals(List.of(INITIALIZED, FACTORY_CALLED, TASK_EXECUTED), eventList);
    }
}
