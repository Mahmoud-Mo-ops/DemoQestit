package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class ExcelToJsonConverter {

	@SuppressWarnings("deprecation")
	public static String convertExcelToJson(String excelFilePath) throws IOException {
		// Opens an Excel file
		FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
		// Uses XSSFWorkbook (for .xlsx files).
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		// Reads the first sheet (getSheetAt(0)).
		Sheet sheet = workbook.getSheetAt(0);
      //Creates an iterator to go through the rows.
		Iterator<Row> rowIterator = sheet.iterator();

		List<Map<String, String>> dataList = new ArrayList<>();
		Row headerRow = rowIterator.next(); // First row as header
		List<String> headers = new ArrayList<>();

		for (Cell cell : headerRow) {
			headers.add(cell.getStringCellValue());
		}

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Map<String, String> dataMap = new LinkedHashMap<>();

			for (int i = 0; i < headers.size(); i++) {
				Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING); // Avoid type mismatch error
				dataMap.put(headers.get(i), cell.getStringCellValue());
			}

			dataList.add(dataMap);
		}

		workbook.close();
		fileInputStream.close();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(dataList);
	}
}
