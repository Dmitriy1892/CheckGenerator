package com.coldfier.data.models;

public record DiscountCard(Long id, int discountPercentage) {

    public DiscountCard {
        if (discountPercentage < 0) throw new IllegalArgumentException("Discount percentage cannot be a negative");
    }
}
