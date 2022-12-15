package com.coldfier.data.data_sources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromoSettingsDataSourceTest {

    @Test
    void getPromoSettings() {
        PromoSettingsDataSource promoSettingsDataSource = new PromoSettingsDataSource();

        Assertions.assertNotNull(promoSettingsDataSource.getPromoSettings());
    }
}