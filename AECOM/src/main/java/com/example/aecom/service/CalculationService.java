package com.example.aecom.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CalculationService {

    public double calculate(double input) {
        try {
            File file = new ClassPathResource("aecom.xlsx").getFile(); // Replace "excel-file.xlsx" with the path to your actual Excel file
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the sheet with the formula is the first one
            Row row = sheet.getRow(0); // Assuming the cells are in the first row
            if (row == null) {
                row = sheet.createRow(0);
            }
            Cell cellA1 = row.getCell(0);
            if (cellA1 == null) {
                cellA1 = row.createCell(0);
            }
            cellA1.setCellValue(input);

            // Assuming the cell with the formula is B1 (second cell in the first row)
            Cell cellB1 = row.getCell(1);
            if (cellB1 == null) {
                throw new RuntimeException("Cell B1 is empty");
            }

            // Refresh all the formulas in the workbook
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.evaluateAll();

            // Get the value of cell B1 after the formula has been evaluated
            double result = cellB1.getNumericCellValue();

            // Save the workbook
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();

            return result;
        } catch (IOException e) {
            throw new RuntimeException("Error while calculating", e);
        }
    }
}
