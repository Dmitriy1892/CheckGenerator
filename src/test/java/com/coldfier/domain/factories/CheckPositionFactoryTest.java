package com.coldfier.domain.factories;

import com.coldfier.data.StorageRepository;
import com.coldfier.data.models.PromoSettings;
import com.coldfier.data.models.Item;
import com.coldfier.domain.factories.CheckPositionFactory;
import com.coldfier.domain.models.check_position.CheckPosition;
import com.coldfier.domain.models.check_position.DefaultCheckPosition;
import com.coldfier.domain.models.check_position.PromoPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CheckPositionFactoryTest {

    @Test
    public void test_createDefaultPosition() {
        Item item = new Item(1L, "Test", 5.5);

        StorageRepository storageRepository = Mockito.mock(StorageRepository.class);

        Mockito.when(storageRepository.isItemInDiscount(1L)).thenReturn(false);

        CheckPositionFactory checkPositionFactory = new CheckPositionFactory(storageRepository);

        CheckPosition createdCheckPosition = checkPositionFactory.create(item, 1);

        Assertions.assertInstanceOf(DefaultCheckPosition.class, createdCheckPosition);
    }

    @Test
    public void test_createDiscountPosition() {
        Item item = new Item(1L, "Test", 5.5);

        StorageRepository storageRepository = Mockito.mock(StorageRepository.class);
        Mockito.when(storageRepository.isItemInDiscount(1L)).thenReturn(true);
        Mockito.when(storageRepository.getPromoSettings())
                .thenReturn(new PromoSettings(5, 10));

        CheckPositionFactory checkPositionFactory = new CheckPositionFactory(storageRepository);

        CheckPosition createdCheckPosition = checkPositionFactory.create(item, 1);

        Assertions.assertInstanceOf(PromoPosition.class, createdCheckPosition);
    }
}
