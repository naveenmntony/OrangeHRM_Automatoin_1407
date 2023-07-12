package com.framework.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static Sheet sheet;
	static Workbook wbook;

	static Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

	public static void intializeExcel() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\OrangeHRM_TestData.xlsx";
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);

		if (path.endsWith("xlsx")) {
			wbook = new XSSFWorkbook(fis);
		} else if (path.endsWith("xls")) {
			wbook = new HSSFWorkbook(fis);
		} else {
			System.out.println("Invalid excel format");
		}

		sheet = wbook.getSheet("TestData");
	}

	public static void readAllDataFromExcel() {
		String value = "";

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, String> cellData = new HashMap<String, String>();
			int cellSize = sheet.getRow(i).getLastCellNum();
			for (int j = 0; j < cellSize; j++) {
				String colName = sheet.getRow(0).getCell(j).getStringCellValue();

				Cell cell = sheet.getRow(i).getCell(j, Row.RETURN_BLANK_AS_NULL);
				if (cell != null) {
					value = sheet.getRow(i).getCell(j).getStringCellValue();
				} else {
					value = "";
				}
				cellData.put(colName, value);
			}
			String testName = sheet.getRow(i).getCell(0).getStringCellValue();

			data.put(testName, cellData);

		}

	}

	public static String readData(String testName, String colName) {

		return data.get(testName).get(colName);

	}

}
