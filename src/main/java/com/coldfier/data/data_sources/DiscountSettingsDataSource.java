package com.coldfier.data.data_sources;

import com.coldfier.data.models.DiscountSettings;

public class DiscountSettingsDataSource {

    private final DiscountSettings discountSettings = new DiscountSettings(5, 10);

    public DiscountSettings getDiscountSettings() {
        return discountSettings;
    }
}
