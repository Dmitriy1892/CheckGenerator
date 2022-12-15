package com.coldfier.di.modules;

import com.coldfier.data.StorageRepository;

public class RepositoriesModule {

    private final DataSourcesModule dataSourcesModule = new DataSourcesModule();

    public StorageRepository provideStorageRepository() {
        return StorageRepository.getInstance(
                dataSourcesModule.providePromoSettingsDataSource(),
                dataSourcesModule.provideDiscountCardsDataSource(),
                dataSourcesModule.provideItemsDataSource(),
                dataSourcesModule.provideMerchantInfoDataSource()
        );
    }
}
