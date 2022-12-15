package com.coldfier.domain.models.check_position;

import com.coldfier.utils.Utils;
import com.coldfier.data.models.PromoSettings;

public class PromoPosition implements CheckPosition {

    private final double fullCost;

    private final int discountPercentage;
    private final CheckPosition checkPosition;

    public PromoPosition(CheckPosition checkPosition, PromoSettings promoSettings) {
        this.checkPosition = checkPosition;

        if (checkPosition.getPositionQuantity() >= promoSettings.itemQuantity()) {
            this.discountPercentage = promoSettings.promoPercentage();
            double discount = checkPosition.getFullCost() * promoSettings.promoPercentage() / 100;
            fullCost = Utils.roundToTwoDecimals(checkPosition.getFullCost() - discount);
        } else {
            this.discountPercentage = 0;
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

        String priceStr = "$" + Utils.formatDoubleToString(checkPosition.getPrice());
        String fullCostStr = "$" + Utils.formatDoubleToString(fullCost);

        return String.format(
                Utils.TABLE_FORMAT,
                checkPosition.getPositionQuantity(),
                checkPosition.getPositionName(),
                Utils.formatStringWithStartSpace(priceStr, 10),
                Utils.formatStringWithStartSpace(discountPercentage + "%", 10),
                Utils.formatStringWithStartSpace(fullCostStr, 10)
        );
    }
}
