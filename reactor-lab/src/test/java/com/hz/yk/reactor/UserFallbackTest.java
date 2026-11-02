package com.hz.yk.reactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class UserFallbackTest {

    private static final String PRIMARY_USER_NAME = "Alice";
    private static final String BACKUP_USER_NAME = "BackupAlice";
    private static final String CACHED_USER_NAME = "CachedAlice";
    private static final String PRIMARY_FAILURE_MESSAGE = "primary query failed";
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testSuccessfulQueryKeepsPrimaryResult() {
        Mono<String> primaryQuery = Mono.just(PRIMARY_USER_NAME);
        PublisherProbe<String> backupProbe = PublisherProbe.of(Mono.just(BACKUP_USER_NAME));
        PublisherProbe<String> cacheProbe = PublisherProbe.of(Mono.just(CACHED_USER_NAME));

        Mono<String> result = primaryQuery
                .switchIfEmpty(backupProbe.mono())
                .onErrorResume(error -> cacheProbe.mono());

        StepVerifier.create(result)
                .expectNext(PRIMARY_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        backupProbe.assertWasNotSubscribed();
        cacheProbe.assertWasNotSubscribed();
    }

    @Test
    public void testEmptyQueryUsesBackup() {
        Mono<String> primaryQuery = Mono.empty();
        PublisherProbe<String> backupProbe = PublisherProbe.of(Mono.just(BACKUP_USER_NAME));
        PublisherProbe<String> cacheProbe = PublisherProbe.of(Mono.just(CACHED_USER_NAME));

        Mono<String> result = primaryQuery
                .switchIfEmpty(backupProbe.mono())
                .onErrorResume(error -> cacheProbe.mono());

        StepVerifier.create(result)
                .expectNext(BACKUP_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        backupProbe.assertWasSubscribed();
        cacheProbe.assertWasNotSubscribed();
    }

    @Test
    public void testFailedQueryUsesCache() {
        Mono<String> primaryQuery = Mono.error(new IllegalStateException(PRIMARY_FAILURE_MESSAGE));
        PublisherProbe<String> backupProbe = PublisherProbe.of(Mono.just(BACKUP_USER_NAME));
        PublisherProbe<String> cacheProbe = PublisherProbe.of(Mono.just(CACHED_USER_NAME));

        Mono<String> result = primaryQuery
                .switchIfEmpty(backupProbe.mono())
                .onErrorResume(error -> cacheProbe.mono());

        StepVerifier.create(result)
                .expectNext(CACHED_USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        backupProbe.assertWasNotSubscribed();
        cacheProbe.assertWasSubscribed();
    }
}
