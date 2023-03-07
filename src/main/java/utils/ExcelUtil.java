package utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ExcelUtil {
    final static Logger excelUtilLogger = LogManager.getLogger(ExcelUtil.class);

    public static String testDataExcelFileName; //Global test data excel file
    public static final String currentDir = System.getProperty("user.dir");  //Main Directory of the project
    public static String testDataExcelPath = null; //Location of Test data excel file
    private static XSSFWorkbook excelWBook; //Excel WorkBook
    private static XSSFSheet excelWSheet; //Excel Sheet
    private static XSSFCell cell; //Excel cell
    private static XSSFRow row; //Excel row
    public static int rowNumber; //Row Number
    public static int columnNumber; //Column Number

    public static String setFilePath(){
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "/src/main/resources/";
        } else if (Platform.getCurrent().toString().contains("WIN")) {
            testDataExcelPath = currentDir + "\\src\\main\\resources\\";
        }
        return testDataExcelPath;
    }

    public static void setCellData(String value, int RowNum, int ColNum) throws Throwable{
        row = excelWSheet.getRow(RowNum);
        cell = row.getCell(ColNum);
        if (cell == null) {
            cell = row.createCell(ColNum);
            cell.setCellValue(value);
        } else {
            cell.setCellValue(value);
        }
        // Constant variables Test Data path and Test Data file name
        FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
        excelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public static String[][] getExcelData(String fileName,String sheetName) throws Throwable {

        String[][] data = null;
        try
        {
            if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
                testDataExcelPath = currentDir + "/src/test/resources/";
            } else if (Platform.getCurrent().toString().contains("WIN")) {
                testDataExcelPath = currentDir + "\\src\\test\\resources\\";
            }
            FileInputStream fis = new FileInputStream(testDataExcelPath + fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows-1][noOfCols];
            for(int i =1; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sh.getRow(i);
                    cell= row.getCell(j);
                    data[i-1][j] = cell.getStringCellValue();
                }
            }
            excelUtilLogger.info("Read Data from Excel File..");
        }
        catch (Throwable t) {
            excelUtilLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error on reading Excel Data..");
        }
        return data;
    }

    public static void writeToCsvFile(Map<String,String> responseList,String fileName){
        try{
            setFilePath();
            FileOutputStream file = new FileOutputStream(testDataExcelPath +fileName);
            OutputStreamWriter fileWriter = new OutputStreamWriter(file, StandardCharsets.UTF_8);
            for (Map.Entry<String, String> entry : responseList.entrySet()) {
                String key = entry.getKey();
                String responseCode = entry.getValue();
                fileWriter.write(key +"--->"+responseCode);
                fileWriter.write("\r\n");
            }
            fileWriter.close();
        }
        catch (Throwable t){
            excelUtilLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occured on writing to CSV file..");
        }
    }
}