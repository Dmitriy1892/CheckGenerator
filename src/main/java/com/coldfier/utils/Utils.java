package com.coldfier.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String TABLE_FORMAT = "%-3s %-25s %-10s %-10s";

    private Utils() {}

    public static double roundToTwoDecimals(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String formatted = df.format(value).replace(",", ".");
        return Double.parseDouble(formatted);
    }

    public static String getDateTimeFromTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(new Date(timestamp));
    }

    public static String getDateFromTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(timestamp);
    }

    public static String getTimeFromTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(timestamp);
    }

    public static String getSymbolsString(char symbol, int quantity) {

        int i = 0;

        StringBuilder output = new StringBuilder();

        while (i <= quantity) {
            output.append(symbol);
            i++;
        }

        return output.toString();
    }

    public static String formatStringWithMaxWidth(String input, int maxWidth) {

        StringBuilder output = new StringBuilder();

        if (input.length() > maxWidth) {
            String out = input.substring(0, (maxWidth - 1));

            output.append(out).append("\n");

            String other = formatStringWithMaxWidth(input.substring(maxWidth, input.length() - 1), maxWidth);
            output.append(other);
        } else if (input.length() == maxWidth) {
            output.append(input).append("\n");
        } else {
            int whitespacesQuantity = maxWidth - input.length();
            String halfWhitespaces = getSymbolsString(' ', whitespacesQuantity / 2);

            output.append(halfWhitespaces)
                    .append(input)
                    .append(getSymbolsString(' ', whitespacesQuantity - halfWhitespaces.length()))
                    .append("\n");
        }

        return output.toString();
    }

    public static String formatStringsWithSpaceBetween(String firstInput, String secondInput, int maxWidth) {

        StringBuilder stringBuilder = new StringBuilder();

        if (firstInput.length() + secondInput.length() < maxWidth) {
            stringBuilder.append(firstInput);
            String whitespaces = getSymbolsString(' ', maxWidth - firstInput.length() - secondInput.length());
            stringBuilder.append(whitespaces)
                    .append(secondInput)
                    .append("\n");
        } else {
            stringBuilder.append(firstInput)
                    .append("\n");

            String whitespaces = getSymbolsString(' ', maxWidth - secondInput.length());

            stringBuilder.append(whitespaces)
                    .append(secondInput)
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
