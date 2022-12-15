package com.coldfier.data.data_sources;

import com.coldfier.data.models.DiscountCard;

import java.util.Map;

public class DiscountCardsDataSource {

    private final Map<Long, DiscountCard> discountCards = Map.of(
            1234L, new DiscountCard(1234L, 5),
            5678L, new DiscountCard(5678L, 10),
            9876L, new DiscountCard(9876L, 15)
    );

    public Map<Long, DiscountCard> getDiscountCards() {
        return discountCards;
    }
}
