package com.coldfier.domain;

import com.coldfier.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserInputParserTest {

    @Test
    public void test_parseItemPair_return_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = getParseItemPairMethod();

        Pair<Long, Integer> result = (Pair<Long, Integer>) privateMethod.invoke(null,"44-11");

        Assertions.assertEquals(44, result.first());
        Assertions.assertEquals(11, result.second());
    }

    private Method getParseItemPairMethod() throws NoSuchMethodException {
        Class<?>[] params = new Class<?>[]{String.class};
        Method privateMethod = UserInputParser.class.getDeclaredMethod("parseItemPair", params);
        privateMethod.setAccessible(true);

        return privateMethod;
    }

    @Test
    public void test_parseUserInput_result_callback_success_with_discount() {

        String[] input = new String[] { "3-3", "1-5", "card-124" };

        ParseResultCallback callback = Mockito.mock(ParseResultCallback.class);
        UserInputParser.parseUserInput(input, callback);
        Mockito.verify(callback).onSuccess(Mockito.anyMap(), Mockito.anyLong());
    }

    @Test
    public void test_parseUserInput_with_null_argument_result_callback_error() {

        String[] input = null;

        ParseResultCallback callback = Mockito.mock(ParseResultCallback.class);
        UserInputParser.parseUserInput(input, callback);
        Mockito.verify(callback).onError(Mockito.anyString());
    }

    @Test
    public void test_parseUserInput_result_callback_success_without_discount() {

        String[] input = new String[] { "3-3", "1-5" };

        ParseResultCallback callback = Mockito.mock(ParseResultCallback.class);
        UserInputParser.parseUserInput(input, callback);
        Mockito.verify(callback).onSuccess(Mockito.anyMap());
    }

    @Test
    public void test_parseUserInput_result_callback_success_file_name() {

        String[] input = new String[] { "qfqfq333" };

        ParseResultCallback callback = Mockito.mock(ParseResultCallback.class);
        UserInputParser.parseUserInput(input, callback);
        Mockito.verify(callback).onSuccess(Mockito.anyString());
    }

    @Test
    public void test_parseUserInput_result_callback_error_file_name() {
        String[] input = new String[] { "qfqfq333`e" };
        parseUserInput_result_callback_error_verifier(input);
    }

    @Test
    public void test_parseUserInput_result_callback_error_file_name2() {
        String[] input = new String[] { "qfqfq333e&" };
        parseUserInput_result_callback_error_verifier(input);
    }

    private void parseUserInput_result_callback_error_verifier(String[] input) {
        ParseResultCallback callback = Mockito.mock(ParseResultCallback.class);
        UserInputParser.parseUserInput(input, callback);
        Mockito.verify(callback).onError(Mockito.anyString());
    }

    private Method getIsFileNameWrongMethod() throws NoSuchMethodException {
        Class<?>[] params = new Class<?>[]{String.class};
        Method privateMethod = UserInputParser.class.getDeclaredMethod("isFileNameWrong", params);
        privateMethod.setAccessible(true);

        return privateMethod;
    }

    @Test
    public void test_isFileNameWrong_input_correct_result_false() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getIsFileNameWrongMethod();

        boolean isFileNameWrong = (boolean) method.invoke(null, "feielle");

        Assertions.assertFalse(isFileNameWrong);
    }

    @Test
    public void test_isFileNameWrong_input_wrong_value_result_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getIsFileNameWrongMethod();

        boolean isFileNameWrong = (boolean) method.invoke(null, "feielle*");

        Assertions.assertTrue(isFileNameWrong);
    }

    @Test
    public void test_isFileNameWrong_input_empty_value_result_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getIsFileNameWrongMethod();

        boolean isFileNameWrong = (boolean) method.invoke(null, "");

        Assertions.assertTrue(isFileNameWrong);
    }
}
