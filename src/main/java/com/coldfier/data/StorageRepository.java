package com.coldfier.data;

import com.coldfier.data.data_sources.DiscountCardsDataSource;
import com.coldfier.data.data_sources.DiscountSettingsDataSource;
import com.coldfier.data.data_sources.ItemsDataSource;
import com.coldfier.data.data_sources.MerchantInfoDataSource;
import com.coldfier.data.models.DiscountCard;
import com.coldfier.data.models.DiscountSettings;
import com.coldfier.data.models.Item;
import com.coldfier.data.models.MerchantInfo;

public class StorageRepository {

    private static final Object SYNC_OBJECT = new Object();
    private static volatile StorageRepository INSTANCE;
    private final DiscountSettingsDataSource discountSettingsDataSource;
    private final DiscountCardsDataSource discountCardsDataSource;
    private final ItemsDataSource itemsDataSource;
    private final MerchantInfoDataSource merchantInfoDataSource;
    private final int vatPercentage;

    private StorageRepository(
            DiscountSettingsDataSource discountSettingsDataSource,
            DiscountCardsDataSource discountCardsDataSource,
            ItemsDataSource itemsDataSource,
            MerchantInfoDataSource merchantInfoDataSource
    ) {
        this.discountSettingsDataSource = discountSettingsDataSource;
        this.discountCardsDataSource = discountCardsDataSource;
        this.itemsDataSource = itemsDataSource;
        this.merchantInfoDataSource = merchantInfoDataSource;
        this.vatPercentage = 17;
    }

    public static StorageRepository getInstance(
            DiscountSettingsDataSource discountSettingsDataSource,
            DiscountCardsDataSource discountCardsDataSource,
            ItemsDataSource itemsDataSource,
            MerchantInfoDataSource merchantInfoDataSource
    ) {
        if (INSTANCE == null) {
            synchronized (SYNC_OBJECT) {
                if (INSTANCE == null) {
                    INSTANCE = new StorageRepository(
                            discountSettingsDataSource,
                            discountCardsDataSource,
                            itemsDataSource,
                            merchantInfoDataSource
                    );
                }

                return INSTANCE;
            }
        } else {
            return INSTANCE;
        }
    }

    public MerchantInfo getMerchantInfo() {
        return merchantInfoDataSource.getMerchantInfo();
    }

    public DiscountSettings getDiscountSettings() {
        return discountSettingsDataSource.getDiscountSettings();
    }

    /**
     * @return Item or null
     */
    public Item getItemById(long itemId) {
        return itemsDataSource.getItems().get(itemId);
    }

    /**
     * @return DiscountCard or null
     */
    public DiscountCard getDiscountCardById(Long cardId) {
        return discountCardsDataSource.getDiscountCards().get(cardId);
    }

    public boolean isItemInDiscount(long itemId) {
        return itemsDataSource.getDiscountItemIds().contains(itemId);
    }

    public int getVatPercentage() {
        return vatPercentage;
    }
}
