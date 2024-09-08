package ext.group3.utilities.Utilities_UI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtils {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    private static Logger LOG = LogManager.getLogger();

    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        try{
            //open Excel file
            FileInputStream ExcelFile = new FileInputStream(path);
            //Access the required test data sheet
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);


        }catch(Exception e){
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getCellData(int rowNum, int colNum){
        Cell cell;

        try{
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellValue = cell.toString();
            return cellValue;
        }catch(Exception e){
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;

        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);

            fileOut.close();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unable to set cell value.");
        }
    }

    public int columnCount() {
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return workSheet.getLastRowNum();
    }

    /**
     * Returns the data from an excel as a 2D Array
     * @return
     */
    public String[][] getDataArray() {

        String[][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    /**
     * Gets all column names
     * @return
     */
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    /**
     * Gets data as a List of Map
     * @return
     */

    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();
        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.getCellStyle().toString());
            }

            data.add(rowMap);
        }

        return data;
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public List<Map<String, String>> getDataListV2() {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> columns = getColumnsNames();

        for (int i = 1; i <= rowCount(); i++) {
            Row row = workSheet.getRow(i);
            if (row == null) continue; // Skip empty rows

            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < columnCount(); j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = Double.toString(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        case BLANK:
                            cellValue = "";
                            break;
                        default:
                            cellValue = cell.toString();
                            break;
                    }
                }

                rowMap.put(columns.get(j), cellValue);
            }
            data.add(rowMap);
        }
        return data;
    }



}

