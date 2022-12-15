package com.coldfier.data.data_sources;

import com.coldfier.data.models.PromoSettings;

public class PromoSettingsDataSource {

    private final PromoSettings promoSettings = new PromoSettings(5, 10);

    public PromoSettings getPromoSettings() {
        return promoSettings;
    }
}
