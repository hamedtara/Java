import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelWriter {
    private String excelFilePath;

    public ExcelWriter(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public void write(String data) {
        Workbook workbook;
        File file = new File(excelFilePath);

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = WorkbookFactory.create(fis);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheet("Sheet1");
        if (sheet == null) {
            sheet = workbook.createSheet("Sheet1");
        }

        int lastRowIndex = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRowIndex + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(data);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setExcelFilePath(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }
}
