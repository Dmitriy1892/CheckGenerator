package com.coldfier.data.models;

public record DiscountSettings(int itemQuantity, int discountPercentage) {

    public DiscountSettings {
        if (itemQuantity < 0 || discountPercentage < 0)
            throw new IllegalArgumentException("Discount percentage and item quantity cannot be a negative");
    }
}
