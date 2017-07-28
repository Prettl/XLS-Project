package ua.prettl.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class Utils {
    public static boolean matchesNumber(String aNumber) {

        final String regex = "\\d{2}\\/\\d{4}$";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(aNumber);

        return matcher.find();
    }

	public static int[] rowRangeFor(File xlsFile, int colNumber) {
		int[] result = {-1, -1};
		
	
		//TODO: should throw an exception here....
		if (!Files.exists(xlsFile.toPath())) return result; 
	
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(xlsFile);

			XSSFSheet sheet = workbook.getSheetAt(0);

			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {

				XSSFRow row = sheet.getRow(i);

				if (row == null)
					continue;

				XSSFCell cell = row.getCell(colNumber);

				if (cell == null)
					continue;

				CellType cellType = cell.getCellTypeEnum();

				switch (cellType) {
				case STRING:
					String value = cell.getStringCellValue();
					boolean isTableNumber = Utils.matchesNumber(value);

					if (isTableNumber && (result[0] == -1)) {
						result[0] = i + 1;
					} else if (isTableNumber) {
						result[1] = i + 1;
					}

					break;

				default:
					break;
				}

			}
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
