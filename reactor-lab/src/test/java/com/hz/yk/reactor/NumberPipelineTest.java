package com.hz.yk.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static com.hz.yk.reactor.NumberPipeline.filterEvenAndScale;

/**
 * @author wuzheng.yk
 * @date 2026/10/30
 */
public class NumberPipelineTest {

    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testNormal() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
        final Flux<Integer> filterFlux = filterEvenAndScale(flux);
        StepVerifier.create(filterFlux).expectNext(20, 40, 60).expectComplete().verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testEmpty() {
        Flux<Integer> flux = Flux.empty();
        StepVerifier.create(filterEvenAndScale(flux)).expectComplete().verify(VERIFY_TIMEOUT);

    }

    @Test
    public void testNegative() {
        Flux<Integer> flux = Flux.just(-2, -1, 0, 1, 2);
        StepVerifier.create(filterEvenAndScale(flux)).expectNext(-20, 0, 20).expectComplete().verify(VERIFY_TIMEOUT);

    }

}
