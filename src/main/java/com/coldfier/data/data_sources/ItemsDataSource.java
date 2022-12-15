package com.coldfier.data.data_sources;

import com.coldfier.data.models.Item;
import java.util.Map;
import java.util.Set;

public class ItemsDataSource {

    private final Map<Long, Item> itemsMap = Map.of(
            1L, new Item(1L, "Lorem Ipsum", 3.55),
            2L, new Item(2L, "Dolor", 2.34),
            3L, new Item(3L, "Sir amet", 3.32),
            4L, new Item(4L, "Consectetur adiping", 10.50),
            5L, new Item(5L, "Elit", 3.12),
            6L, new Item(6L, "Suspendisse eget", 0.45),
            7L, new Item(7L, "Placerat massa", 37.34),
            8L, new Item(8L, "Aenean vulputate", 17.43),
            9L, new Item(9L, "Quam ac eleifend", 1.50),
            10L, new Item(10L, "Magna in", 54.30)
    );

    private final Set<Long> discountItemIds = Set.of(1L, 4L, 5L, 7L);

    public Map<Long, Item> getItems() {
        return itemsMap;
    }

    public Set<Long> getDiscountItemIds() {
        return discountItemIds;
    }
}
