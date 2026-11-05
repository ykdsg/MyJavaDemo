package com.hz.yk.reactor.product;

import java.io.Serializable;
import java.math.BigDecimal;

public final class ProductDetailTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String TO_STRING_FORMAT = "ProductDetailTO{productName=%s, price=%s, stockStatus=%s}";

    private final String productName;
    private final BigDecimal price;
    private final StockStatusEnum stockStatus;

    ProductDetailTO(String productName, BigDecimal price, StockStatusEnum stockStatus) {
        this.productName = productName;
        this.price = price;
        this.stockStatus = stockStatus;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public StockStatusEnum getStockStatus() {
        return stockStatus;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, productName, price, stockStatus);
    }
}
