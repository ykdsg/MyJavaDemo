package com.hz.yk.reactor.product;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import reactor.core.publisher.Mono;

public final class ProductDetailService {

    private static final Duration STOCK_TIMEOUT = Duration.ofMillis(200);
    private static final String PRODUCT_REQUIRED = "productQuery must not be null";
    private static final String PRICE_REQUIRED = "priceQuery must not be null";
    private static final String CACHED_PRICE_REQUIRED = "cachedPriceQuery must not be null";
    private static final String STOCK_REQUIRED = "stockQuery must not be null";
    private static final String PRODUCT_NOT_FOUND = "product not found";
    private static final String PRICE_UNAVAILABLE = "price unavailable";

    public Mono<ProductDetailTO> getDetail(Mono<String> productQuery, Mono<BigDecimal> priceQuery,
                                         Mono<BigDecimal> cachedPriceQuery, Mono<StockStatusEnum> stockQuery) {
        Objects.requireNonNull(productQuery, PRODUCT_REQUIRED);
        Objects.requireNonNull(priceQuery, PRICE_REQUIRED);
        Objects.requireNonNull(cachedPriceQuery, CACHED_PRICE_REQUIRED);
        Objects.requireNonNull(stockQuery, STOCK_REQUIRED);

        Mono<String> product = productQuery
                .switchIfEmpty(Mono.error(() -> new ProductQueryException(PRODUCT_NOT_FOUND)));
        Mono<BigDecimal> price = priceQuery
                .onErrorResume(error -> cachedPriceQuery)
                .switchIfEmpty(Mono.error(() -> new ProductQueryException(PRICE_UNAVAILABLE)));
        Mono<StockStatusEnum> stock = stockQuery
                .timeout(STOCK_TIMEOUT)
                .onErrorReturn(TimeoutException.class, StockStatusEnum.UNKNOWN)
                .defaultIfEmpty(StockStatusEnum.UNKNOWN);

        return Mono.zip(product, price, stock)
                .map(tuple -> new ProductDetailTO(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}
