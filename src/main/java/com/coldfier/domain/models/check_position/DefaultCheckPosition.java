package com.coldfier.domain.models.check_position;

import com.coldfier.data.models.Item;
import com.coldfier.utils.Utils;

public class DefaultCheckPosition implements CheckPosition {

    private final String positionName;
    private final double price;
    private final int quantity;
    private final double fullCost;

    public DefaultCheckPosition(Item item, int quantity) {
        this.positionName = item.name();
        this.price = item.price();
        this.quantity = quantity;
        fullCost = item.price() * quantity;
    }

    @Override
    public String getPositionName() {
        return positionName;
    }

    @Override
    public int getPositionQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public double getFullCost() {
        return fullCost;
    }

    @Override
    public String toString() {
        return String.format(Utils.TABLE_FORMAT, quantity, positionName, "$" + price, "$" + fullCost);
    }
}
