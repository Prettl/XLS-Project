package ua.prettl.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateFullTime {
	private static final String PATH = "C:\\Users\\aeu.adm\\Documents\\salary_august\\OTHER_1C_PI.xlsm";
	private static final String NEW_PATH = "C:\\Users\\aeu.adm\\Documents\\salary_august\\OTHER_1C_PI_tabel.xlsm";
	
	public static void main(String... args) {
			
		int from = 2;
		int to = 6;
		int columnFrom = 3;
		int columnTo = 33;
		
		if (Files.exists(Paths.get(PATH))) {
			//1.
			//saveUniqueScheduleValuesToXLSX(scanSchedule(PATH, from, to, columnFrom, columnTo), PATH);
			//2.
			writeTimeTableForFile(NEW_PATH, PATH, createTimeMapperFromPath(PATH), from, to, columnFrom, columnTo);
		}
	}
	
	 public static List<String> scanSchedule(String target, int from, int to, int columnFrom, int columnTo) {
	        List<String> differentCellsValues = new ArrayList<>();

	        try {
	            XSSFWorkbook wbReader = new XSSFWorkbook(new FileInputStream(target));

	            XSSFSheet activeSheet = wbReader.getSheetAt(0);

	            for (int rowIndex = from; rowIndex <= to; rowIndex++) {
	                XSSFRow row = activeSheet.getRow(rowIndex);
	                if (null == row) continue;
	            
	                for (int colIndex = columnFrom; colIndex <= columnTo; colIndex++) {
	                    XSSFCell cell = row.getCell(colIndex);
	                    if (cell == null) continue;
	                    String rawValue = "";
	                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
	                        rawValue = String.valueOf(cell.getNumericCellValue());
	                    } else {
	                        rawValue = cell.getStringCellValue();
	                    }
	                    System.out.println(rawValue);
	                    String[] strings = rawValue.split("\\n");

	                    for (String current : strings) {
	                        if (!current.isEmpty()) {
	                            differentCellsValues.add(current);
	                        }
	                    }
	                }
	            }
	            wbReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return differentCellsValues;
	    }

	    public static void saveUniqueScheduleValuesToXLSX(List<String> differentCellsValues, String stringPath) {

	        Path path = Paths.get(stringPath);

	        XSSFWorkbook wbWritable = null;
	        try {
	            wbWritable = new XSSFWorkbook(new FileInputStream(stringPath));


	            XSSFSheet sheet = wbWritable.getSheet("map");

	            if (sheet == null) {
	                sheet = wbWritable.createSheet("map");
	            }

	            Set<String> uniqueSet = new HashSet<>(differentCellsValues);


	            int rowIndex = 0;
	            for (String s : uniqueSet) {
	                XSSFRow row = sheet.createRow(rowIndex++);
	                row.createCell(0).setCellValue(s);
	            }

	            FileOutputStream fos = new FileOutputStream(path.toFile());

	            wbWritable.write(fos);

	            fos.close();

	            wbWritable.close();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	    

	    private static Map<String, Double> createTimeMapperFromPath(String stringPath) {
	        Map<String, Double> map = new HashMap<>();

	        Path path = Paths.get(stringPath);

	        if (Files.exists(path)) {

	            try {
	                XSSFWorkbook wbReadable = new XSSFWorkbook(new FileInputStream(path.toFile()));

	                XSSFSheet sheet = wbReadable.getSheet("map");

	                for (int rowIndex = sheet.getFirstRowNum(); rowIndex <= sheet.getLastRowNum();rowIndex++) {
	                    XSSFRow row = sheet.getRow(rowIndex);
	                    String key = row.getCell(0).getStringCellValue();
	                    double value = 0d;

	                    XSSFCell cell = row.getCell(1);
	                    if (cell != null) {
	                        value = cell.getNumericCellValue();
	                    }
	                    map.put(key, value);
	                }

	                wbReadable.close();

	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	        } else {
	            //TODO: log this situation
	        }

	        System.out.println(map.size());
	        return Collections.unmodifiableMap(map);

	    }

	    private static void writeTimeTableForFile(String newStringPath, String sourcePath, Map<String, Double> mapper, int from, int to, int columnFrom, int columnTo) {
	    	if (!Files.exists(Paths.get(newStringPath))) {
	    		try {
					Files.copy(Paths.get(sourcePath), Paths.get(newStringPath), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}

			try {
	
				XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(newStringPath));
	
				XSSFSheet sheet = workbook.getSheetAt(0);
	
				boolean isModified = false;
	
				for (int rowIndex = from; rowIndex <= to; rowIndex++) {
	
					XSSFRow row = sheet.getRow(rowIndex);
	
					for (int colIndex = columnFrom; colIndex <= columnTo; colIndex++) {
						XSSFCell cell = row.getCell(colIndex);
						if (cell == null)
							continue;
	
						String cellValue = "";
	
						if (cell.getCellTypeEnum() == CellType.NUMERIC) {
							cellValue = String.valueOf(cell.getNumericCellValue());
						} else {
							cellValue = cell.getStringCellValue();
						}
	
						String[] keys = cellValue.split("\\n");
	
						double hours = 0d;
						for (String key : keys) {
							if (!key.isEmpty()) {
								System.out.println(key);
	//							System.out.println(mapper);
								Double value = mapper.get(key);
								hours += value;
							}
						}
	
						cell.setCellType(CellType.NUMERIC);
						cell.setCellValue(hours);
						isModified = true;
					}
	
				}
	
				if (isModified) {
					FileOutputStream fos = new FileOutputStream(newStringPath);
	
					workbook.write(fos);
	
					fos.close();
	
					workbook.close();
				}
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
