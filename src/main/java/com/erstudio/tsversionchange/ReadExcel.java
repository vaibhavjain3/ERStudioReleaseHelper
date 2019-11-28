package main.java.com.erstudio.tsversionchange;

import main.java.com.erstudio.tsversionchange.model.ExcelModel;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;

/**
 * @author rajat.batra
 * @description reads excel file
 */
public class ReadExcel {

    public List<ExcelModel> readExcel(String SAMPLE_XLSX_FILE_PATH) throws IOException, InvalidPropertiesFormatException {

        List<ExcelModel> fileList = new ArrayList<>();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

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
                    //serial number
                    dataFormatter.formatCellValue(cell1);
                    Cell cell2 = cellIterator.next();
                    excelModel.setFilepath(dataFormatter.formatCellValue(cell2));
                    Cell cell3 = cellIterator.next();
                    excelModel.setFullVersion(dataFormatter.formatCellValue(cell3));
                    Cell cell4 = cellIterator.next();
                    excelModel.setPatchVersion(dataFormatter.formatCellValue(cell4));
                    Cell cell5 = cellIterator.next();
                    excelModel.setStringToReplaceFormat(dataFormatter.formatCellValue(cell5));
                    fileList.add(excelModel);
                }
            }
        });
        workbook.close();
        return fileList;
    }
}

