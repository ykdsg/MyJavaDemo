package com.hz.yk.reactor;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRetryTest {

    private static final String PRIMARY_USER_NAME = "Alice";
    private static final String CACHED_USER_NAME = "CachedAlice";
    private static final String PRIMARY_FAILURE_MESSAGE = "primary query failed";
    private static final int MAX_RETRIES = 2;
    private static final int EXPECTED_MAX_ATTEMPTS = 3;
    private static final int FAILURES_BEFORE_SUCCESS = 2;
    private static final int SINGLE_CALL = 1;
    private static final int NO_CALLS = 0;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testRetryBeforeFallbackRetriesPrimaryQuery() {
        AtomicInteger primaryCallCount = new AtomicInteger();
        AtomicInteger cacheCallCount = new AtomicInteger();
        Mono<String> primaryQuery = Mono.defer(() -> {
            primaryCallCount.incrementAndGet();
            return Mono.error(new IllegalStateException(PRIMARY_FAILURE_MESSAGE));
        });
        Mono<String> cacheQuery = Mono.fromCallable(() -> {
            cacheCallCount.incrementAndGet();
            return CACHED_USER_NAME;
        });

        Mono<String> result = primaryQuery
                .retry(MAX_RETRIES)
                .onErrorResume(error -> cacheQuery);

        assertEquals(NO_CALLS, primaryCallCount.get());
        assertEquals(NO_CALLS, cacheCallCount.get());

        StepVerifier.create(result)
                .expectNext(CACHED_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(EXPECTED_MAX_ATTEMPTS, primaryCallCount.get());
        assertEquals(SINGLE_CALL, cacheCallCount.get());
    }

    @Test
    public void testFallbackBeforeRetrySkipsPrimaryRetry() {
        AtomicInteger primaryCallCount = new AtomicInteger();
        AtomicInteger cacheCallCount = new AtomicInteger();
        Mono<String> primaryQuery = Mono.defer(() -> {
            primaryCallCount.incrementAndGet();
            return Mono.error(new IllegalStateException(PRIMARY_FAILURE_MESSAGE));
        });
        Mono<String> cacheQuery = Mono.fromCallable(() -> {
            cacheCallCount.incrementAndGet();
            return CACHED_USER_NAME;
        });

        Mono<String> result = primaryQuery
                .onErrorResume(error -> cacheQuery)
                .retry(MAX_RETRIES);

        StepVerifier.create(result)
                .expectNext(CACHED_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(SINGLE_CALL, primaryCallCount.get());
        assertEquals(SINGLE_CALL, cacheCallCount.get());
    }

    @Test
    public void testRetryStopsAfterPrimaryQuerySucceeds() {
        AtomicInteger primaryCallCount = new AtomicInteger();
        AtomicInteger cacheCallCount = new AtomicInteger();
        Mono<String> primaryQuery = Mono.defer(() -> {
            int attempt = primaryCallCount.incrementAndGet();
            if (attempt <= FAILURES_BEFORE_SUCCESS) {
                return Mono.error(new IllegalStateException(PRIMARY_FAILURE_MESSAGE));
            }
            return Mono.just(PRIMARY_USER_NAME);
        });
        Mono<String> cacheQuery = Mono.fromCallable(() -> {
            cacheCallCount.incrementAndGet();
            return CACHED_USER_NAME;
        });

        Mono<String> result = primaryQuery
                .retry(MAX_RETRIES)
                .onErrorResume(error -> cacheQuery);

        StepVerifier.create(result)
                .expectNext(PRIMARY_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertEquals(EXPECTED_MAX_ATTEMPTS, primaryCallCount.get());
        assertEquals(NO_CALLS, cacheCallCount.get());
    }
}
