package ua.prettl.integrated;

import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import ua.prettl.utils.Utils;

@DisplayName("Find configuration info about timetable in a file")
@RunWith(JUnitPlatform.class)
class FindFirstAndLastRowTest {
	
	private static String XLSX_TEST_DIR = "src/test/resources/manufacturer";
	
	@DisplayName(value="Group of test for checking range of rows:")
	@ParameterizedTest(name= "{index} -> file: {0} (firstRow:{1} lastRow:{2})")
	@MethodSource("fileToTestProvider")
	void testWithMultiArgMethodSource(String name, int expectedFirstRow, int expectedLastRow) {
		
		int[] rows = Utils.rowRangeFor(Paths.get(XLSX_TEST_DIR).resolve(name).toFile(), 1);
		
		Assertions.assertEquals(expectedFirstRow, rows[0]);
		Assertions.assertEquals(expectedLastRow, rows[1]);
		
		
	
	}

	static Stream<Arguments> fileToTestProvider() {
	    return Stream.of(
	    		
	    		Arguments.of("11 бр.xlsx", 7, 22),
	    		Arguments.of("12 бр.xlsx", 7, 18),
	    		Arguments.of("13 бр.xlsx", 7, 19),
	    		Arguments.of("21 бр.xlsx", 7, 15),
	    		Arguments.of("22 бр.xlsx", 7, 18),
	    		Arguments.of("23 бр.xlsx", 7, 18),
	    		Arguments.of("31 бр.xlsx", 7, 28),
	    		Arguments.of("32 бр.xlsx", 7, 29),
	    		Arguments.of("33 бр.xlsx", 7, 29),
	    		Arguments.of("41.xlsx", 7, 38),
	    		Arguments.of("42.xlsx", 7, 43),
	    		Arguments.of("43.xlsx", 7, 42),
	    		Arguments.of("44.xlsx", 7, 39),
	    		Arguments.of("45.xlsx", 7, 42),
	    		Arguments.of("46.xlsx", 7, 32),
	    		Arguments.of("47.xlsx", 7, 30),
	    		Arguments.of("49.xlsx", 7, 13),
	    		Arguments.of("50.xlsx", 7, 38),
	    		Arguments.of("empty.xlsx", -1, -1),
	    		Arguments.of("50_with_skipped_rows.xlsx", 7, 41),
	    		Arguments.of("50_ended_with_empty_rows.xlsx", 7, 41)
	    		
	    		);
	}
	
}
