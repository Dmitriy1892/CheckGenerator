package com.coldfier.data.data_sources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsDataSourceTest {

    @Test
    void getItems() {
        ItemsDataSource itemsDataSource = new ItemsDataSource();

        Assertions.assertNotNull(itemsDataSource.getItems());
    }

    @Test
    void getDiscountItemIds() {
        ItemsDataSource itemsDataSource = new ItemsDataSource();

        Assertions.assertNotNull(itemsDataSource.getDiscountItemIds());
    }
}