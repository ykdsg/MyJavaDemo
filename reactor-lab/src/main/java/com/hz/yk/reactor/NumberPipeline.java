package com.hz.yk.reactor;

import reactor.core.publisher.Flux;

/**
 * @author wuzheng.yk
 * @date 2026/10/30
 */
public class NumberPipeline {

    public static Flux<Integer> filterEvenAndScale(Flux<Integer> integerFlux) {
        if (null == integerFlux) {
            throw new IllegalArgumentException();
        }
        return integerFlux.filter(t -> t % 2 == 0).map(t -> t * 10);
    }
}
