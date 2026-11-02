package com.hz.yk.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * map和flatMap
 *
 * @author wuzheng.yk
 * @date 2026/10/30
 */
public class UserMappingTest {

    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);
    Mono<String> user1 = Mono.just("Alice");
    Mono<String> user2 = Mono.empty();
    Flux<Mono<String>> source = Flux.just(user1, user2);

    /**
     * map：把返回值当成一个元素
     */
    @Test
    public void testUserMapping() {
        final Flux<Mono<String>> monoFlux = source.map(user -> user);
        StepVerifier.create(monoFlux).expectNext(user1).expectNext(user2).expectComplete().verify(VERIFY_TIMEOUT);

    }

    /**
     * flatMap：组合内部序列，交付里面的元素
     */
    @Test
    public void testUserFlatMap() {
        final Flux<String> stringFlux = source.flatMap(v -> v);
        StepVerifier.create(stringFlux).expectNext("Alice").expectComplete().verify(VERIFY_TIMEOUT);
    }

}
