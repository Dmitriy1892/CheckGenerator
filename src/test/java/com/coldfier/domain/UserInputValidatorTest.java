package com.coldfier.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserInputValidatorTest {
    
    @Test
    public void test_isItemPair_returns_true() {
        boolean isResultTrue = UserInputValidator.isItemPair("54225-24324325");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_non_trimmed_returns_true() {
        boolean isResultTrue = UserInputValidator.isItemPair("    54225-24324325    ");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_is_all_zeros_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("000-000");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_is_empty_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_is_decimal_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("444.3-313");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_is_null_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair(null);

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_not_digit_returns_false() {

        boolean isResultTrue = UserInputValidator.isItemPair("5a-3");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_without_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("232 2332");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_with_multiple_dashes_and_digits_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("232-232-222-222");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_input_with_multiple_dashes_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("232--232");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isItemPair_if_first_char_is_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isItemPair("-212-323");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_correct_input_returns_true() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("card-1214");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_correct_input_uppercase_returns_true() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("CARD-1214");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_correct_input_camelcase_returns_true() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("cArD-1214");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_is_decimal_returns_true() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("cArD-1214.3");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_without_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("cArD1214");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_non_card_before_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("cerd-1214");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_non_digit_after_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("card-faef1214");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_with_double_dash_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("card--1214");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_is_null_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId(null);

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_non_trimmed_returns_true() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("      card-1231    ");

        Assertions.assertTrue(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_empty_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("");

        Assertions.assertFalse(isResultTrue);
    }

    @Test
    public void test_isDiscountCardId_if_input_first_digit_is_zero_returns_false() {
        boolean isResultTrue = UserInputValidator.isDiscountCardId("card-0223");

        Assertions.assertFalse(isResultTrue);
    }
}
