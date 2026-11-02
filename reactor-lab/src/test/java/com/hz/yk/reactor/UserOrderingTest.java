package com.hz.yk.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author wuzheng.yk
 * @date 2026/10/30
 */
public class UserOrderingTest {

    private static final String ALICE_NAME = "Alice";
    private static final String BOB_NAME = "Bob";
    private static final Duration ALICE_DELAY = Duration.ofMillis(300);
    private static final Duration BOB_DELAY = Duration.ofMillis(100);
    private static final Duration CLOCK_TICK = Duration.ofMillis(1);
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testUserFlatMapOrdering() {
        StepVerifier.withVirtualTime(() -> createUserQueries().flatMap(userQuery -> userQuery)).expectSubscription()
                .expectNoEvent(BOB_DELAY.minus(CLOCK_TICK)).thenAwait(CLOCK_TICK).expectNext(BOB_NAME)
                .expectNoEvent(ALICE_DELAY.minus(BOB_DELAY).minus(CLOCK_TICK)).thenAwait(CLOCK_TICK)
                .expectNext(ALICE_NAME).expectComplete().verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testUserConcatMapOrdering() {
        StepVerifier.withVirtualTime(() -> createUserQueries().concatMap(userQuery -> userQuery)).expectSubscription()
                .expectNoEvent(ALICE_DELAY.minus(CLOCK_TICK)).thenAwait(CLOCK_TICK).expectNext(ALICE_NAME)
                .expectNoEvent(BOB_DELAY.minus(CLOCK_TICK)).thenAwait(CLOCK_TICK).expectNext(BOB_NAME).expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    private Flux<Mono<String>> createUserQueries() {
        Mono<String> aliceQuery = Mono.just(ALICE_NAME).delayElement(ALICE_DELAY);
        Mono<String> bobQuery = Mono.just(BOB_NAME).delayElement(BOB_DELAY);
        return Flux.just(aliceQuery, bobQuery);
    }

}
