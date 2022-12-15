package com.coldfier.domain.models;

import com.coldfier.utils.Utils;
import com.coldfier.domain.models.check_position.CheckPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Check {

    private String title;
    private String merchantName;
    private String address;
    private String phone;
    private Long cashierId;
    private Long timestamp;
    private final List<CheckPosition> checkPositions;
    private double costWithoutVat;
    private int vatPercentage;
    private double vatCost;
    private int cardDiscountPercentage;
    private double cardDiscountCost;
    private double totalCost;

    private Check() {
        title = "";
        merchantName = "";
        address = "";
        phone = "";
        cashierId = -1L;
        checkPositions = new ArrayList<>();
        costWithoutVat = 0.0;
        vatPercentage = 0;
        cardDiscountPercentage = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<CheckPosition> getCheckPositions() {
        return Collections.unmodifiableList(checkPositions);
    }

    public double getCostWithoutVat() {
        return costWithoutVat;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public double getVatCost() {
        return vatCost;
    }

    public int getCardDiscountPercentage() {
        return cardDiscountPercentage;
    }

    public double getCardDiscountCost() {
        return cardDiscountCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {

        int stringMaxWidth = 51;

        String divider = Utils.getSymbolsString('-', stringMaxWidth) + "\n";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(divider)
                .append(Utils.formatStringWithMaxWidth(title, stringMaxWidth))
                .append(Utils.formatStringWithMaxWidth(merchantName, stringMaxWidth))
                .append(Utils.formatStringWithMaxWidth(address, stringMaxWidth))
                .append(Utils.formatStringWithMaxWidth("Tel: " + phone, stringMaxWidth))
                .append("\n");

        String date = Utils.formatStringsWithSpaceBetween(
                "CASHIER: No" + cashierId,
                "DATE: " + Utils.getDateFromTimestamp(timestamp),
                stringMaxWidth
        );

        stringBuilder.append(date);

        String time = Utils.formatStringsWithSpaceBetween(
                "",
                "TIME: " + Utils.getTimeFromTimestamp(timestamp),
                stringMaxWidth
        ).substring(2);

        stringBuilder.append(time).append(divider);

        String tableHeader = String.format(
                Utils.TABLE_FORMAT,
                "QTY",
                "DESCRIPTION",
                "PRICE",
                "TOTAL"
        ) + "\n";

        stringBuilder.append(tableHeader);

        for (CheckPosition position : checkPositions) {
            stringBuilder.append(position.toString())
                    .append("\n");
        }

        stringBuilder.append(divider)
                .append(divider);

        String taxable = Utils.formatStringsWithSpaceBetween(
                "TAXABLE TOT.",
                "$" + costWithoutVat,
                stringMaxWidth
        );

        stringBuilder.append(taxable);

        String vat = Utils.formatStringsWithSpaceBetween(
                "VAT" + vatPercentage + "%",
                "$" + vatCost,
                stringMaxWidth
        );

        stringBuilder.append(vat);

        String total = Utils.formatStringsWithSpaceBetween(
                "TOTAL",
                "$" + totalCost,
                stringMaxWidth
        );

        stringBuilder.append(total)
                .append("\n")
                .append(divider);

        return stringBuilder.toString();
    }

    public static class Builder {

        private final Check check;

        public Builder() {
            check = new Check();
        }

        public Builder setTitle(String title) {
            check.title = title.toUpperCase();
            return this;
        }

        public Builder setMerchantName(String merchantName) {
            check.merchantName = merchantName;
            return this;
        }

        public Builder setAddress(String address) {
            check.address = address;
            return this;
        }

        public Builder setPhone(String phone) {
            check.phone = phone;
            return this;
        }

        public Builder setCashierId(Long cashierId) {
            check.cashierId = cashierId < 0 ? -1 : cashierId;
            return this;
        }

        public Builder addCheckPositions(List<CheckPosition> positions) {
            check.checkPositions.addAll(positions);
            return this;
        }

        public Builder setVatPercentage(int vatPercentage) {
            check.vatPercentage = Math.max(vatPercentage, 0);
            return this;
        }

        public Builder setCardDiscountPercentage(int cardDiscountPercentage) {
            check.cardDiscountPercentage = Math.max(cardDiscountPercentage, 0);
            return this;
        }

        public Check build() {

            check.timestamp = System.currentTimeMillis();

            double taxablePrice = 0.0;
            for (CheckPosition position : check.checkPositions) {
                taxablePrice += position.getFullCost();
            }
            check.costWithoutVat = Utils.roundToTwoDecimals(taxablePrice);

            double cardDiscount = (check.cardDiscountPercentage > 0) ? ((float) check.cardDiscountPercentage / 100) : 0;
            check.cardDiscountCost = Utils.roundToTwoDecimals(check.costWithoutVat * cardDiscount);

            double vat = (check.vatPercentage > 0) ? ((float) check.vatPercentage / 100) : 1;
            double priceWithoutVat = check.costWithoutVat - check.cardDiscountCost;
            check.vatCost = Utils.roundToTwoDecimals(priceWithoutVat * vat);

            check.totalCost =
                    Utils.roundToTwoDecimals(check.costWithoutVat - check.cardDiscountCost + check.vatCost);

            return check;
        }
    }
}
