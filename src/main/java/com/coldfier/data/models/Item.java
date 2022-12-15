package com.coldfier.data.models;

import com.coldfier.utils.Utils;

public record Item(long id, String name, double price) {
    public Item {
        if (id < 0 || price < 0.0) throw new IllegalArgumentException("Id and price cannot be a negative");
        price = Utils.roundToTwoDecimals(price);
    }
}
