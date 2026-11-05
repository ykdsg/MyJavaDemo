package com.hz.yk.reactor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReactorModelTest {

    private static final String FIRST_CHUNK = "hello";
    private static final String SECOND_CHUNK = " reactor";
    private static final String THIRD_CHUNK = " world";
    private static final String UPPERCASE_CHUNK = "HELLO";
    private static final List<String> CHUNK_LIST = List.of(FIRST_CHUNK, SECOND_CHUNK, THIRD_CHUNK);
    private static final int CHUNK_COUNT = 3;
    private static final int NO_CALLS = 0;
    private static final int SINGLE_CALL = 1;
    private static final long NO_DEMAND = 0L;
    private static final long REQUEST_ONE = 1L;
    private static final long REQUEST_REMAINING = 2L;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testMonoListPublishesOneElementContainingAllChunks() {
        Publisher<List<String>> publisher = Mono.just(CHUNK_LIST);

        StepVerifier.create(publisher, NO_DEMAND)
                .expectSubscription()
                .thenRequest(REQUEST_ONE)
                .expectNext(CHUNK_LIST)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testFluxPublishesChunksAsSeparateElements() {
        Publisher<String> publisher = Flux.fromIterable(CHUNK_LIST);

        StepVerifier.create(publisher)
                .expectNext(FIRST_CHUNK, SECOND_CHUNK, THIRD_CHUNK)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testVoidMonoRunsTaskWithoutPublishingAnElement() {
        AtomicBoolean saved = new AtomicBoolean();
        Mono<Void> saveTask = Mono.fromRunnable(() -> saved.set(true));

        assertFalse(saved.get());

        StepVerifier.create(saveTask)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertTrue(saved.get());
    }

    @Test
    public void testAssemblySubscriptionAndRequestedDeliveryAreDifferentStages() {
        AtomicInteger subscriptionCount = new AtomicInteger();
        AtomicInteger sourceCallCount = new AtomicInteger();
        AtomicInteger mappingCallCount = new AtomicInteger();
        AtomicReference<Thread> sourceThread = new AtomicReference<>();
        Thread subscribingThread = Thread.currentThread();
        Mono<String> pipeline = Mono.fromSupplier(() -> {
                    sourceCallCount.incrementAndGet();
                    sourceThread.set(Thread.currentThread());
                    return FIRST_CHUNK;
                })
                .map(chunk -> {
                    mappingCallCount.incrementAndGet();
                    return chunk.toUpperCase(Locale.ROOT);
                })
                .doOnSubscribe(subscription -> subscriptionCount.incrementAndGet());

        assertEquals(NO_CALLS, subscriptionCount.get());
        assertEquals(NO_CALLS, sourceCallCount.get());
        assertEquals(NO_CALLS, mappingCallCount.get());

        StepVerifier.create(pipeline, NO_DEMAND)
                .expectSubscription()
                .then(() -> {
                    assertEquals(SINGLE_CALL, subscriptionCount.get());
                    assertEquals(NO_CALLS, sourceCallCount.get());
                    assertEquals(NO_CALLS, mappingCallCount.get());
                })
                .thenRequest(REQUEST_ONE)
                .expectNext(UPPERCASE_CHUNK)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(SINGLE_CALL, sourceCallCount.get());
        assertEquals(SINGLE_CALL, mappingCallCount.get());
        assertSame(subscribingThread, sourceThread.get());
    }

    @Test
    public void testSubscriberReceivesSubscriptionAndControlsDemand() {
        Publisher<String> publisher = Flux.fromIterable(CHUNK_LIST);
        AtomicReference<Subscription> subscriptionReference = new AtomicReference<>();
        List<String> receivedChunkList = new ArrayList<>(CHUNK_COUNT);
        AtomicBoolean completed = new AtomicBoolean();
        BaseSubscriber<String> subscriber = new BaseSubscriber<String>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                subscriptionReference.set(subscription);
            }

            @Override
            protected void hookOnNext(String chunk) {
                receivedChunkList.add(chunk);
            }

            @Override
            protected void hookOnComplete() {
                completed.set(true);
            }
        };

        try {
            publisher.subscribe(subscriber);
            Subscription subscription = subscriptionReference.get();

            assertNotNull(subscription);
            assertTrue(receivedChunkList.isEmpty());
            assertFalse(completed.get());

            subscription.request(REQUEST_ONE);

            assertEquals(List.of(FIRST_CHUNK), receivedChunkList);
            assertFalse(completed.get());

            subscription.request(REQUEST_REMAINING);

            assertEquals(CHUNK_LIST, receivedChunkList);
            assertTrue(completed.get());
        } finally {
            subscriber.dispose();
        }
    }

    @Test
    public void testCancellationStopsDeliveryWithoutNormalCompletion() {
        AtomicBoolean cancelled = new AtomicBoolean();
        AtomicBoolean completed = new AtomicBoolean();
        List<String> receivedChunkList = new ArrayList<>(CHUNK_COUNT);
        Flux<String> publisher = Flux.fromIterable(CHUNK_LIST)
                .doOnNext(receivedChunkList::add)
                .doOnCancel(() -> cancelled.set(true))
                .doOnComplete(() -> completed.set(true));

        StepVerifier.create(publisher, NO_DEMAND)
                .expectSubscription()
                .thenRequest(REQUEST_ONE)
                .expectNext(FIRST_CHUNK)
                .thenCancel()
                .verify(VERIFY_TIMEOUT);

        assertEquals(List.of(FIRST_CHUNK), receivedChunkList);
        assertTrue(cancelled.get());
        assertFalse(completed.get());
    }
}
