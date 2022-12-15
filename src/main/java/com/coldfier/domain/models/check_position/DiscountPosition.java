package com.coldfier.domain.models.check_position;

import com.coldfier.utils.Utils;
import com.coldfier.data.models.DiscountSettings;

public class DiscountPosition implements CheckPosition {

    private final double fullCost;
    private final CheckPosition checkPosition;

    public DiscountPosition(CheckPosition checkPosition, DiscountSettings discountSettings) {
        this.checkPosition = checkPosition;

        if (checkPosition.getPositionQuantity() >= discountSettings.itemQuantity()) {
            double discount = checkPosition.getFullCost() * discountSettings.discountPercentage() / 100;
            fullCost = Utils.roundToTwoDecimals(checkPosition.getFullCost() - discount);
        } else {
            fullCost = checkPosition.getFullCost();
        }
    }

    @Override
    public String getPositionName() {
        return checkPosition.getPositionName();
    }

    @Override
    public int getPositionQuantity() {
        return checkPosition.getPositionQuantity();
    }

    @Override
    public double getPrice() {
        return checkPosition.getPrice();
    }

    @Override
    public double getFullCost() {
        return fullCost;
    }

    @Override
    public String toString() {
        return String.format(
                Utils.TABLE_FORMAT,
                checkPosition.getPositionQuantity(),
                checkPosition.getPositionName(),
                "$" + checkPosition.getPrice(),
                "$" + fullCost
        );
    }
}
