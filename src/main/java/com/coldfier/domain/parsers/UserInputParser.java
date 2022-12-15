package com.coldfier.domain.parsers;

import com.coldfier.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputParser {

    private UserInputParser() {}

    public static void parseUserInput(String[] input, ParseResultCallback parseResultCallback) {

        if (parseResultCallback == null) return;

        if (input == null || input.length == 0) {
            parseResultCallback.onError("No arguments passed");
            return;
        }

        Map<Long, Integer> itemsMap = new HashMap<>();
        long discountCardId = -1L;
        String fileName = "";

        for (String value : input) {
            if (UserInputValidator.isItemPair(value)) {
                Pair<Long, Integer> parsedPair = parseItemPair(value);
                itemsMap.put(parsedPair.first(), parsedPair.second());
                continue;
            }

            if (UserInputValidator.isDiscountCardId(value) && discountCardId == -1L) {
                discountCardId = parseDiscountCardId(value);
                continue;
            }

            if (fileName.isEmpty()) {
                fileName = value.trim();
            }
        }

        sendResult(itemsMap, discountCardId, fileName, parseResultCallback);
    }

    private static Pair<Long, Integer> parseItemPair(String value) {
        String[] array = value.split("-");
        long itemId = Long.parseLong(array[0]);
        int quantity = Integer.parseInt(array[1]);
        return new Pair<>(itemId, quantity);
    }

    private static Long parseDiscountCardId(String value) {
        String stringId = value.toLowerCase().replace("card-", "");
        return Long.parseLong(stringId);
    }

    private static void sendResult(
            Map<Long, Integer> itemsMap,
            long discountCardId,
            String fileName,
            ParseResultCallback parseResultCallback
    ) {
        if (itemsMap.isEmpty()) {
            if (discountCardId >= 0) {
                parseResultCallback.onError("Discount card cannot be passed without items in arguments");
                return;
            }

            if (fileName.isEmpty()) {
                parseResultCallback.onError("Invalid input. Please specify file name or items with/wo discount card");
            } else if (isFileNameWrong(fileName)) {
                parseResultCallback.onError("Filename cannot contains whitespaces or next symbols: ^ * & % - ` '");
            } else {
                parseResultCallback.onSuccess(fileName);
            }
            return;
        }

        if (!fileName.isEmpty()) {
            String errorMessage = "Invalid input. File name passed with items in arguments" +
                    " or incorrect card data passed into arguments";
            parseResultCallback.onError(errorMessage);
            return;
        }

        if (discountCardId >= 0) {
            parseResultCallback.onSuccess(itemsMap, discountCardId);
        } else {
            parseResultCallback.onSuccess(itemsMap);
        }
    }

    private static Boolean isFileNameWrong(String value) {

        if (value.isEmpty()) return true;

        Pattern pattern = Pattern.compile("([\\^\\*\\&\\%\\-\\`\\']+)");
        Matcher matcher = pattern.matcher(value);

        return matcher.find();
    }
}
