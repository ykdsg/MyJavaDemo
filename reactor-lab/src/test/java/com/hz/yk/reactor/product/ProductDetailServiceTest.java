package com.hz.yk.reactor.product;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailServiceTest {

    private static final String PRODUCT_NAME = "Reactor book";
    private static final BigDecimal PRIMARY_PRICE = new BigDecimal("99.00");
    private static final BigDecimal CACHED_PRICE = new BigDecimal("89.00");
    private static final String PRODUCT_NOT_FOUND = "product not found";
    private static final String PRICE_UNAVAILABLE = "price unavailable";
    private static final String PRIMARY_FAILURE = "price service failed";
    private static final String CACHE_FAILURE = "price cache failed";
    private static final String STOCK_FAILURE = "stock service failed";
    private static final String PRODUCT_REQUIRED = "productQuery must not be null";
    private static final String PRICE_REQUIRED = "priceQuery must not be null";
    private static final String CACHED_PRICE_REQUIRED = "cachedPriceQuery must not be null";
    private static final String STOCK_REQUIRED = "stockQuery must not be null";
    private static final String NULL_ARGUMENT_CASES = "nullArgumentCases";
    private static final int EQUAL_COMPARISON = 0;
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);
    private static final Duration BEFORE_STOCK_TIMEOUT = Duration.ofMillis(199);
    private static final Duration FINAL_MILLISECOND = Duration.ofMillis(1);
    private static final Duration PRODUCT_DELAY = Duration.ofMillis(50);
    private static final Duration PRICE_DELAY = Duration.ofMillis(80);
    private static final Duration STOCK_DELAY = Duration.ofMillis(100);
    private static final Duration BEFORE_AGGREGATION = Duration.ofMillis(99);

    private final ProductDetailService service = new ProductDetailService();

    @Test
    public void testSuccessDoesNotSubscribeToCache() {
        PublisherProbe<BigDecimal> cache = PublisherProbe.of(Mono.just(CACHED_PRICE));

        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME), Mono.just(PRIMARY_PRICE),
                        cache.mono(), Mono.just(StockStatusEnum.IN_STOCK)))
                .assertNext(detail -> assertDetail(detail, PRIMARY_PRICE, StockStatusEnum.IN_STOCK))
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        cache.assertWasNotSubscribed();
    }

    @Test
    public void testMissingProductFailsInsteadOfCompletingEmpty() {
        StepVerifier.create(service.getDetail(Mono.empty(), Mono.just(PRIMARY_PRICE),
                        Mono.just(CACHED_PRICE), Mono.just(StockStatusEnum.IN_STOCK)))
                .expectErrorMatches(error -> error instanceof ProductQueryException
                        && PRODUCT_NOT_FOUND.equals(error.getMessage()))
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testPriceFailureUsesCachedPrice() {
        PublisherProbe<BigDecimal> cache = PublisherProbe.of(Mono.just(CACHED_PRICE));
        Mono<BigDecimal> price = Mono.error(new IllegalStateException(PRIMARY_FAILURE));

        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME), price,
                        cache.mono(), Mono.just(StockStatusEnum.IN_STOCK)))
                .assertNext(detail -> assertDetail(detail, CACHED_PRICE, StockStatusEnum.IN_STOCK))
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        cache.assertWasSubscribed();
    }

    @Test
    public void testStockTimeoutUsesUnknownAndCancelsSource() {
        AtomicBoolean stockCancelled = new AtomicBoolean();

        StepVerifier.withVirtualTime(() -> service.getDetail(Mono.just(PRODUCT_NAME), Mono.just(PRIMARY_PRICE),
                        Mono.just(CACHED_PRICE), Mono.<StockStatusEnum>never()
                                .doOnCancel(() -> stockCancelled.set(true))))
                .expectSubscription()
                .expectNoEvent(BEFORE_STOCK_TIMEOUT)
                .then(() -> assertFalse(stockCancelled.get()))
                .thenAwait(FINAL_MILLISECOND)
                .assertNext(detail -> assertDetail(detail, PRIMARY_PRICE, StockStatusEnum.UNKNOWN))
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertTrue(stockCancelled.get());
    }

    @Test
    public void testCacheFailureIsNotSwallowed() {
        IllegalStateException cacheFailure = new IllegalStateException(CACHE_FAILURE);

        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME),
                        Mono.error(new IllegalStateException(PRIMARY_FAILURE)), Mono.error(cacheFailure),
                        Mono.just(StockStatusEnum.IN_STOCK)))
                .consumeErrorWith(error -> {
                    assertSame(cacheFailure, error);
                    assertEquals(CACHE_FAILURE, error.getMessage());
                })
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testEmptyPrimaryPriceFailsWithoutUsingCache() {
        PublisherProbe<BigDecimal> cache = PublisherProbe.of(Mono.just(CACHED_PRICE));

        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME), Mono.empty(),
                        cache.mono(), Mono.just(StockStatusEnum.IN_STOCK)))
                .expectErrorMatches(error -> error instanceof ProductQueryException
                        && PRICE_UNAVAILABLE.equals(error.getMessage()))
                .verify(VERIFY_TIMEOUT);

        cache.assertWasNotSubscribed();
    }

    @Test
    public void testEmptyCachedPriceFailsAfterPrimaryError() {
        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME),
                        Mono.error(new IllegalStateException(PRIMARY_FAILURE)), Mono.empty(),
                        Mono.just(StockStatusEnum.IN_STOCK)))
                .expectErrorMatches(error -> error instanceof ProductQueryException
                        && PRICE_UNAVAILABLE.equals(error.getMessage()))
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testEmptyStockUsesUnknown() {
        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME), Mono.just(PRIMARY_PRICE),
                        Mono.just(CACHED_PRICE), Mono.empty()))
                .assertNext(detail -> assertDetail(detail, PRIMARY_PRICE, StockStatusEnum.UNKNOWN))
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testUnexpectedStockErrorIsNotSwallowed() {
        IllegalStateException stockFailure = new IllegalStateException(STOCK_FAILURE);

        StepVerifier.create(service.getDetail(Mono.just(PRODUCT_NAME), Mono.just(PRIMARY_PRICE),
                        Mono.just(CACHED_PRICE), Mono.error(stockFailure)))
                .consumeErrorWith(error -> {
                    assertSame(stockFailure, error);
                    assertEquals(STOCK_FAILURE, error.getMessage());
                })
                .verify(VERIFY_TIMEOUT);
    }

    @Test
    public void testAsyncSourcesAggregateAfterSlowestSource() {
        StepVerifier.withVirtualTime(() -> service.getDetail(
                        Mono.just(PRODUCT_NAME).delayElement(PRODUCT_DELAY),
                        Mono.just(PRIMARY_PRICE).delayElement(PRICE_DELAY),
                        Mono.just(CACHED_PRICE),
                        Mono.just(StockStatusEnum.OUT_OF_STOCK).delayElement(STOCK_DELAY)))
                .expectSubscription()
                .expectNoEvent(BEFORE_AGGREGATION)
                .thenAwait(FINAL_MILLISECOND)
                .assertNext(detail -> assertDetail(detail, PRIMARY_PRICE, StockStatusEnum.OUT_OF_STOCK))
                .expectComplete()
                .verify(VERIFY_TIMEOUT);
    }

    @ParameterizedTest
    @MethodSource(NULL_ARGUMENT_CASES)
    public void testNullQueryIsRejectedImmediately(Mono<String> product, Mono<BigDecimal> price,
                                                 Mono<BigDecimal> cachedPrice, Mono<StockStatusEnum> stock,
                                                 String expectedMessage) {
        NullPointerException error = assertThrows(NullPointerException.class,
                () -> service.getDetail(product, price, cachedPrice, stock));

        assertEquals(expectedMessage, error.getMessage());
    }

    private static Stream<Arguments> nullArgumentCases() {
        Mono<String> product = Mono.just(PRODUCT_NAME);
        Mono<BigDecimal> price = Mono.just(PRIMARY_PRICE);
        Mono<BigDecimal> cachedPrice = Mono.just(CACHED_PRICE);
        Mono<StockStatusEnum> stock = Mono.just(StockStatusEnum.IN_STOCK);

        return Stream.of(
                Arguments.of(null, price, cachedPrice, stock, PRODUCT_REQUIRED),
                Arguments.of(product, null, cachedPrice, stock, PRICE_REQUIRED),
                Arguments.of(product, price, null, stock, CACHED_PRICE_REQUIRED),
                Arguments.of(product, price, cachedPrice, null, STOCK_REQUIRED));
    }

    private void assertDetail(ProductDetailTO detail, BigDecimal expectedPrice, StockStatusEnum expectedStock) {
        assertEquals(PRODUCT_NAME, detail.getProductName());
        assertEquals(EQUAL_COMPARISON, expectedPrice.compareTo(detail.getPrice()));
        assertEquals(expectedStock, detail.getStockStatus());
    }
}
