package com.coldfier.data.models;

public record PromoSettings(int itemQuantity, int promoPercentage) {

    public PromoSettings {
        if (itemQuantity < 0 || promoPercentage < 0)
            throw new IllegalArgumentException("Promo percentage and item quantity cannot be a negative");
    }
}
