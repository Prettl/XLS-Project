package ua.prettl.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import ua.prettl.utils.Utils;


@DisplayName("Simple test suite")
@RunWith(JUnitPlatform.class)
class FirstTest {

    @Test
    @DisplayName("for table number 00/1001 test should be passed")
    void test1() {
        String number = "00/3003";

        Assertions.assertTrue(Utils.matchesNumber(number));
    }

    @ParameterizedTest(name = "{index}, {0}")
    @ValueSource(strings = { "00/0010", "00/1231", "10/0001", "00/1111" })
    void testWithStringParameter(String argument) {
        Assertions.assertTrue(Utils.matchesNumber(argument));
    }

    @ParameterizedTest(name = "{index}, {0}")
    @ValueSource(strings = { "f0/0010", "001231", "33/fff001", "00/f111" })
    @DisplayName("This is'nt matched cases")
    void testWithStringParameterShouldNotMatch(String argument) {
        Assertions.assertTrue(!Utils.matchesNumber(argument));
    }
    
    @DisplayName("From csv")
    @ParameterizedTest(name = "{index}, argument = {0}, actual={1}")
    @CsvFileSource(resources = {"/test_matches_number.csv"})
    void testWithStringParameter(String argument, boolean result) {
        Assertions.assertEquals(result, Utils.matchesNumber(argument));
    }

}
