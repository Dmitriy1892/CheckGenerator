package com.coldfier.di.modules;

import com.coldfier.data.data_sources.DiscountCardsDataSource;
import com.coldfier.data.data_sources.PromoSettingsDataSource;
import com.coldfier.data.data_sources.ItemsDataSource;
import com.coldfier.data.data_sources.MerchantInfoDataSource;

public class DataSourcesModule {

    public DiscountCardsDataSource provideDiscountCardsDataSource() {
        return new DiscountCardsDataSource();
    }

    public PromoSettingsDataSource providePromoSettingsDataSource() {
        return new PromoSettingsDataSource();
    }

    public ItemsDataSource provideItemsDataSource() {
        return new ItemsDataSource();
    }

    public MerchantInfoDataSource provideMerchantInfoDataSource() {
        return new MerchantInfoDataSource();
    }
}
