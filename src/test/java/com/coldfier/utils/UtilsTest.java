package com.coldfier.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.TimeZone;

public class UtilsTest {

    @Test
    public void test_roundToTwoDecimals_return_not_more_two_symbols_after_pointer() {
        double input = 4.12423141221;
        double result = Utils.roundToTwoDecimals(input);

        String[] array = String.valueOf(result).split("\\.");
        int lastDigitsLength = array[1].length();

        Assertions.assertTrue(lastDigitsLength <= 2);
    }

    @Test
    public void test_roundToTwoDecimals_return_round_to_up() {
        double input = 4.12512;
        double result = Utils.roundToTwoDecimals(input);

        Assertions.assertEquals(4.13, result);
    }

    @Test
    public void test_roundToTwoDecimals_return_round_to_down() {
        double input = 4.12412;
        double result = Utils.roundToTwoDecimals(input);

        Assertions.assertEquals(4.12, result);
    }

    @Test
    public void test_formatDoubleToString_input_many_decimals_return_two_decimals() {
        double input = 4.12513213;

        String result = Utils.formatDoubleToString(input);

        Assertions.assertEquals("4.13", result);
    }

    @Test
    public void test_formatDoubleToString_input_one_decimal_return_two_decimals() {
        double input = 4.1;

        String result = Utils.formatDoubleToString(input);

        Assertions.assertEquals("4.10", result);
    }

    @Test
    public void test_getDateTimeFromTimestamp_return_correct() {
        long timestamp = 0000000000 - TimeZone.getDefault().getRawOffset();
        String result = Utils.getDateTimeFromTimestamp(timestamp);
        Assertions.assertEquals("1970-01-01-00-00-00", result);
    }

    @Test
    public void test_getDateFromTimestamp_return_correct() {
        long timestamp = 0000000000 - TimeZone.getDefault().getRawOffset();
        String result = Utils.getDateFromTimestamp(timestamp);
        Assertions.assertEquals("01/01/1970", result);
    }

    @Test
    public void test_getTimeFromTimestamp_return_correct() {
        long timestamp = 0000000000 - TimeZone.getDefault().getRawOffset();
        String result = Utils.getTimeFromTimestamp(timestamp);
        Assertions.assertEquals("00:00:00", result);
    }

    @Test
    public void test_getSymbolsString_return_correct() {
        char input = '1';
        int times = 5;

        String result = Utils.getSymbolsString(input, times);
        Assertions.assertEquals("11111", result);
    }

    @Test
    public void test_formatStringWithStartEndSpaces_return_correct_length() {
        String input = "FOUR";
        int maxWidth = 13;

        String result = Utils.formatStringWithStartEndSpaces(input, maxWidth);
        Assertions.assertEquals(maxWidth, result.length());
    }

    @Test
    public void test_formatStringWithStartEndSpaces_return_correct() {
        String input = "FOUR";
        int maxWidth = 12;

        String result = Utils.formatStringWithStartEndSpaces(input, maxWidth);
        Assertions.assertEquals(
                "    FOUR    ",
                result
        );
    }

    @Test
    public void test_formatStringsWithSpaceBetween_return_correct_length() {
        String first = "ONE";
        String second = "TWO";
        int maxWidth = 9;

        String result = Utils.formatStringsWithSpaceBetween(first, second, maxWidth);

        Assertions.assertEquals(maxWidth, result.length());
    }

    @Test
    public void test_formatStringsWithSpaceBetween_return_correct_value() {
        String first = "ONE";
        String second = "TWO";
        int maxWidth = 9;

        String result = Utils.formatStringsWithSpaceBetween(first, second, maxWidth);

        Assertions.assertEquals("ONE   TWO", result);
    }

    @Test
    public void test_formatStringWithStartSpace_return_correct_length() {
        String input = "SOME";
        int maxWidth = 10;

        String result = Utils.formatStringWithStartSpace(input, maxWidth);

        Assertions.assertEquals(maxWidth, result.length());
    }

    @Test
    public void test_formatStringWithStartSpace_return_correct_value() {
        String input = "SOME";
        int maxWidth = 10;

        String result = Utils.formatStringWithStartSpace(input, maxWidth);

        Assertions.assertEquals("      SOME", result);
    }
}
