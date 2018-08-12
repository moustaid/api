package com.ositel.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ositel.pojo.InputJSON;
import com.ositel.pojo.Output;
import com.ositel.pojo.RootResponse;

public class UtilityHelper {

	public String excelToJsonFile(String fileTarget, String nameFile) throws JsonGenerationException,
			JsonMappingException, IOException, EncryptedDocumentException, InvalidFormatException {

		FileInputStream inp = new FileInputStream(fileTarget);
		Workbook workbook = WorkbookFactory.create(inp);

		// Get the first Sheet.
		Sheet sheet = workbook.getSheetAt(0);
		RootResponse response = new RootResponse();
		Output output = new Output();
		InputJSON inputJson = new InputJSON();
		inputJson.setFileName(nameFile);
		output.setFileName(nameFile);

		ArrayList<String> headerColumn = new ArrayList<>();
		List<String> linesValue = null;
		List<List<String>> linesValues = new ArrayList<>();

		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {

			Row row = sheet.getRow(i);

			linesValue = new ArrayList<>();
			for (Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext();) {

				Cell cell = cellsIT.next();
				if (i == 0) {

					headerColumn.add(cell.getStringCellValue());
				} else {

					linesValue.add(cell.getStringCellValue());
				}

			}

			if (i != 0) {
				linesValues.add(linesValue);
			}
		}
             //Construire la reponse JSON
		output.setHeaderColumn(headerColumn);
		output.setLinesValue(linesValues);
		response.setOutput(output);
		response.setInputJSON(inputJson);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonobject = ow.writeValueAsString(response);

		return jsonobject;
	}

	public String updateCellValue(String fileTarget, String nameFile, int rows, int cells, String values)
			throws JsonGenerationException, JsonMappingException, IOException, EncryptedDocumentException,
			InvalidFormatException {


		FileInputStream inp = new FileInputStream(fileTarget);
		Workbook workbook = WorkbookFactory.create(inp);

		// recuperation de la premiere sheet.
		Sheet sheet = workbook.getSheetAt(0);
		RootResponse response = new RootResponse();
		Output output = new Output();
		InputJSON inputJson = new InputJSON();
		inputJson.setFileName(nameFile);
		output.setFileName(nameFile);

		ArrayList<String> headerColumn = new ArrayList<>();
		List<String> linesValue = null;
		List<List<String>> linesValues = new ArrayList<>();

		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {

			Row row = sheet.getRow(i);

			linesValue = new ArrayList<>();
			for (Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext();) {

				Cell cell = cellsIT.next();
				if (i == 0) {

					headerColumn.add(cell.getStringCellValue());
				}

				// condition pour modification des valeurs rows cell en param
				else if (i == rows && cell.getColumnIndex() == cells) {

					linesValue.add(values);
				}

				else {

					linesValue.add(cell.getStringCellValue());
				}

			}

			if (i != 0) {
				linesValues.add(linesValue);
			}
		}

		output.setHeaderColumn(headerColumn);
		output.setLinesValue(linesValues);
		response.setOutput(output);
		response.setInputJSON(inputJson);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonobject = ow.writeValueAsString(response.getOutput());

		return jsonobject;
	}

}
