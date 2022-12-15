package com.coldfier.domain;

import com.coldfier.data.StorageRepository;
import com.coldfier.domain.models.check_position.CheckPosition;
import com.coldfier.domain.models.check_position.DefaultCheckPosition;
import com.coldfier.data.models.PromoSettings;
import com.coldfier.data.models.Item;
import com.coldfier.domain.models.check_position.PromoPosition;

public class CheckPositionFactory {

    private final PromoSettings promoSettings;
    private final StorageRepository storageRepository;

    public CheckPositionFactory(
            StorageRepository storageRepository
    ) {
        this.promoSettings = storageRepository.getPromoSettings();
        this.storageRepository = storageRepository;
    }

    public CheckPosition create(Item item, int quantity) {
        CheckPosition checkPosition = new DefaultCheckPosition(item, quantity);

        boolean isItemInDiscount = storageRepository.isItemInDiscount(item.id());
        if (isItemInDiscount) {
            checkPosition = new PromoPosition(checkPosition, promoSettings);
        }

        return checkPosition;
    }
}
