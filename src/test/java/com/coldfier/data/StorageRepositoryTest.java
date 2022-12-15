package com.coldfier.data;

import com.coldfier.data.data_sources.DiscountCardsDataSource;
import com.coldfier.data.data_sources.ItemsDataSource;
import com.coldfier.data.data_sources.MerchantInfoDataSource;
import com.coldfier.data.data_sources.PromoSettingsDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StorageRepositoryTest {

    @Test
    public void test_getInstance_return_single_instance() {
        StorageRepository storageRepository1 = getStorageRepository();
        StorageRepository storageRepository2 = getStorageRepository();

        Assertions.assertEquals(storageRepository1, storageRepository2);
    }
    private StorageRepository getStorageRepository() {
        PromoSettingsDataSource promoSettingsDataSource = Mockito.mock(PromoSettingsDataSource.class);
        DiscountCardsDataSource discountCardsDataSource = Mockito.mock(DiscountCardsDataSource.class);
        ItemsDataSource itemsDataSource = Mockito.mock(ItemsDataSource.class);
        MerchantInfoDataSource merchantInfoDataSource = Mockito.mock(MerchantInfoDataSource.class);

        return StorageRepository.getInstance(
                promoSettingsDataSource, discountCardsDataSource,
                itemsDataSource, merchantInfoDataSource
        );
    }
}
