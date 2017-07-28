package ua.prettl.integrated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import ua.prettl.utils.Utils;

@DisplayName("Find configuration info about timetable in a file")
@RunWith(JUnitPlatform.class)
class FindFirstAndLastRowTest {
	
	private int colTabNumber = 2 - 1;
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String XLSX_FILE = "src/test/resources/11 бр.xlsx";
	
	
	
	@BeforeAll
	static void setupForAll() {
		try {
			workbook = new XSSFWorkbook(XLSX_FILE);
			sheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterAll
	static void destroyResources() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("File should exists")
	void test1(TestInfo info) {
		Assertions.assertTrue(Files.exists(Paths.get(XLSX_FILE)));
	}
	
	@Test
	@DisplayName("find first and last row with tab number")
	void test2() {
		int expectedFirstRow = 7;
		int expectedLastRow = 22;
		
		int actualFirstRow = -1;
		int actualLastRow = -1;
		
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
						
			XSSFRow row = sheet.getRow(i);
			
			if (row == null) continue;
			
			XSSFCell cell = row.getCell(colTabNumber);
			
			if (cell == null) continue; 
			
			CellType cellType = cell.getCellTypeEnum();
			
			switch (cellType) {
			case STRING:
				String value = cell.getStringCellValue();
				boolean isTableNumber = Utils.matchesNumber(value);
				
				if (isTableNumber && (actualFirstRow == -1)) {
					actualFirstRow = i + 1;
				} else if (isTableNumber && (i == sheet.getLastRowNum())) {
					actualLastRow = i + 1;
				}
				
				break;

			default:
				break;
			}
			
			
			
			
			
			
		}
		
		
		
		Assertions.assertEquals(expectedFirstRow, actualFirstRow);
		Assertions.assertEquals(expectedLastRow, actualLastRow);
		
		
		
		
		
		
	}
	
}
