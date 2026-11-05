package com.hz.yk.reactor;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserBackpressureTest {

    private static final long INITIAL_REQUEST = 0L;
    private static final int NO_ELEMENTS = 0;
    private static final int FIRST_VALUE = 1;
    private static final int SECOND_VALUE = 2;
    private static final int THIRD_VALUE = 3;
    private static final int FOURTH_VALUE = 4;
    private static final int FIFTH_VALUE = 5;
    private static final int ELEMENT_COUNT = 5;
    private static final int FIRST_BATCH_SIZE = 2;
    private static final int NEXT_BATCH_SIZE = 1;
    private static final int REQUESTED_BEFORE_CANCEL = FIRST_BATCH_SIZE + NEXT_BATCH_SIZE;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testZeroInitialDemandEmitsNothingBeforeRequest() {
        AtomicInteger emittedCount = new AtomicInteger();
        Flux<Integer> source = Flux.range(FIRST_VALUE, ELEMENT_COUNT)
                .doOnNext(value -> emittedCount.incrementAndGet());

        StepVerifier.create(source, INITIAL_REQUEST)
                .expectSubscription()
                .then(() -> assertEquals(NO_ELEMENTS, emittedCount.get()))
                .thenCancel()
                .verify(VERIFY_TIMEOUT);

        assertEquals(NO_ELEMENTS, emittedCount.get());
    }

    @Test
    public void testBatchedRequestsResumeFromNextValue() {
        AtomicInteger emittedCount = new AtomicInteger();
        Flux<Integer> source = Flux.range(FIRST_VALUE, ELEMENT_COUNT)
                .doOnNext(value -> emittedCount.incrementAndGet());

        StepVerifier.create(source, INITIAL_REQUEST)
                .expectSubscription()
                .then(() -> assertEquals(NO_ELEMENTS, emittedCount.get()))
                .thenRequest(FIRST_BATCH_SIZE)
                .expectNext(FIRST_VALUE, SECOND_VALUE)
                .then(() -> assertEquals(FIRST_BATCH_SIZE, emittedCount.get()))
                .thenRequest(NEXT_BATCH_SIZE)
                .expectNext(THIRD_VALUE)
                .then(() -> assertEquals(REQUESTED_BEFORE_CANCEL, emittedCount.get()))
                .thenRequest(FIRST_BATCH_SIZE)
                .expectNext(FOURTH_VALUE, FIFTH_VALUE)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(ELEMENT_COUNT, emittedCount.get());
    }

    @Test
    public void testCancellationStopsAfterRequestedValues() {
        AtomicInteger emittedCount = new AtomicInteger();
        AtomicBoolean cancelled = new AtomicBoolean();
        AtomicBoolean completed = new AtomicBoolean();
        Flux<Integer> source = Flux.range(FIRST_VALUE, ELEMENT_COUNT)
                .doOnNext(value -> emittedCount.incrementAndGet())
                .doOnCancel(() -> cancelled.set(true))
                .doOnComplete(() -> completed.set(true));

        StepVerifier.create(source, INITIAL_REQUEST)
                .expectSubscription()
                .thenRequest(FIRST_BATCH_SIZE)
                .expectNext(FIRST_VALUE, SECOND_VALUE)
                .thenRequest(NEXT_BATCH_SIZE)
                .expectNext(THIRD_VALUE)
                .thenCancel()
                .verify(VERIFY_TIMEOUT);

        assertEquals(REQUESTED_BEFORE_CANCEL, emittedCount.get());
        assertTrue(cancelled.get());
        assertFalse(completed.get());
    }
}
