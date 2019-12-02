package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.tsversionchange.model.ExcelModel;
import main.java.com.erstudio.tsversionchange.model.VersionFormat;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rajat.batra
 * @description reads excel file and returns list of files to be updated
 */
public class ProcessExcel {
    List<ExcelModel> processedExcelList;

    public ProcessExcel(String excelFilePath) {
        this.processedExcelList = processExcelFile(excelFilePath);
    }

    public List<ExcelModel> getProcessedExcelList() {
        return processedExcelList;
    }

    public void setProcessedExcelList(List<ExcelModel> processedExcelList) {
        this.processedExcelList = processedExcelList;
    }

    public List<ExcelModel> processExcelFile(String SAMPLE_XLSX_FILE_PATH) {

        List<ExcelModel> fileList = new ArrayList<>();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

            // Retrieving the number of sheets in the Workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            // iterating over Rows and Columns
            sheet.forEach(row -> {
                //ignore the first header row
                if (row.getRowNum() != 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    ExcelModel excelModel = new ExcelModel();
                    while (cellIterator.hasNext()) {
                        Cell cell1 = cellIterator.next();
                        // serial number
                        dataFormatter.formatCellValue(cell1);
                        // file path
                        Cell cell2 = cellIterator.next();
                        excelModel.setFilepath(dataFormatter.formatCellValue(cell2));
                        // full version : true/false
                        Cell cell3 = cellIterator.next();
                        excelModel.setFullVersion(dataFormatter.formatCellValue(cell3));
                        // patch version : true/false
                        Cell cell4 = cellIterator.next();
                        excelModel.setPatchVersion(dataFormatter.formatCellValue(cell4));
                        // string to replace format
                        Cell cell5 = cellIterator.next();
                        VersionFormat versionFormat = new VersionFormat(dataFormatter.formatCellValue(cell5));
                        excelModel.setVersionFormat(versionFormat);
                        fileList.add(excelModel);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }
}