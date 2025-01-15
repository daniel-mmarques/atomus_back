package com.godev.atomus.entity.product;

public record ProductData(Long id, String name, boolean isCoveredFgc) {
    public ProductData(Product product) {
        this(product.getId(), product.getName(), product.isCoveredFgc());
    }
}
