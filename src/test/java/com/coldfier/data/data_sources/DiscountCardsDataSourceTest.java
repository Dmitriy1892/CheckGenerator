package com.coldfier.data.data_sources;

import com.coldfier.data.models.DiscountCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCardsDataSourceTest {

    @Test
    void getDiscountCards() {
        DiscountCardsDataSource discountCardsDataSource = new DiscountCardsDataSource();
        Map<Long, DiscountCard> discountCardMap = discountCardsDataSource.getDiscountCards();

        assertNotNull(discountCardMap);
    }
}