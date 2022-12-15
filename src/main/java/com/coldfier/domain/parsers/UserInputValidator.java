package com.coldfier.domain.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputValidator {

    private UserInputValidator() {}

    static Boolean isItemPair(String value) {
        if (value == null) return false;
        Pattern pattern = Pattern.compile("([1-9])([0-9]*)-([1-9])([0-9]*)");
        Matcher matcher = pattern.matcher(value.trim());
        return matcher.matches();
    }

    static Boolean isDiscountCardId(String value) {
        if (value == null) return false;
        Pattern pattern = Pattern.compile("card-([1-9])([0-9]*)");
        Matcher matcher = pattern.matcher(value.toLowerCase().trim());
        return matcher.matches();
    }
}
