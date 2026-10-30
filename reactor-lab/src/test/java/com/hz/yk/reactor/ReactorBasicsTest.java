package com.hz.yk.reactor;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReactorBasicsTest {

    private static final String USER_NAME = "Alice";
    private static final String FAILURE_MESSAGE = "demo source failed";
    private static final int INITIAL_CALL_COUNT = 0;
    private static final int FIRST_CALL_COUNT = 1;
    private static final int SECOND_CALL_COUNT = 2;
    private static final int THIRD_VALUE = 3;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    void monoShouldEmitOneValueThenComplete() {
        Mono<String> source = Mono.just(USER_NAME);

        StepVerifier.create(source)
                .expectNext(USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    void fluxShouldEmitValuesInOrderThenComplete() {
        Flux<Integer> source = Flux.just(FIRST_CALL_COUNT, SECOND_CALL_COUNT, THIRD_VALUE);

        StepVerifier.create(source)
                .expectNext(FIRST_CALL_COUNT, SECOND_CALL_COUNT, THIRD_VALUE)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    void emptyMonoShouldCompleteWithoutValues() {
        StepVerifier.create(Mono.empty())
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    void errorMonoShouldFailWithoutNormalCompletion() {
        Mono<String> source = Mono.error(new IllegalStateException(FAILURE_MESSAGE));

        StepVerifier.create(source)
                .expectErrorMatches(error -> error instanceof IllegalStateException
                        && FAILURE_MESSAGE.equals(error.getMessage()))
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    void callableShouldExecuteOnceForEachSubscription() {
        AtomicInteger callCount = new AtomicInteger();
        Mono<Integer> source = Mono.fromCallable(callCount::incrementAndGet);

        assertEquals(INITIAL_CALL_COUNT, callCount.get());

        StepVerifier.create(source)
                .expectNext(FIRST_CALL_COUNT)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
        assertEquals(FIRST_CALL_COUNT, callCount.get());

        StepVerifier.create(source)
                .expectNext(SECOND_CALL_COUNT)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
        assertEquals(SECOND_CALL_COUNT, callCount.get());
    }

    @Test
    void justShouldCaptureAnEagerlyComputedValue() {
        AtomicInteger callCount = new AtomicInteger();
        Mono<Integer> source = Mono.just(callCount.incrementAndGet());

        assertEquals(FIRST_CALL_COUNT, callCount.get());

        StepVerifier.create(source)
                .expectNext(FIRST_CALL_COUNT)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
        StepVerifier.create(source)
                .expectNext(FIRST_CALL_COUNT)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(FIRST_CALL_COUNT, callCount.get());
    }

    @Test
    void doOnNextShouldExecuteOnlyWhenAValueIsDelivered() {
        AtomicInteger observedCount = new AtomicInteger();
        Mono<String> source = Mono.just(USER_NAME)
                .doOnNext(userName -> observedCount.incrementAndGet());

        assertEquals(INITIAL_CALL_COUNT, observedCount.get());

        StepVerifier.create(source)
                .expectNext(USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(FIRST_CALL_COUNT, observedCount.get());
    }
}
