package utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private static File file;
    private static FileInputStream inputStream;
    private static FileOutputStream outputStream;
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static XSSFRow row;

    // Writes the Excel workbook to the output file.
    public static void WriteExcel() throws IOException {
        closeResource(outputStream); // Close previous output stream if exists

        outputStream = new FileOutputStream(file); // Create a new output stream for the file
        wb.write(outputStream); // Write the workbook to the output stream
        outputStream.close(); // Close the output stream

        closeResource(wb); // Close the workbook
    }

    // To select a sheet by its name in an Excel file
    public static void selectSheetName(String sheetName) throws Exception {
        file = new File("./Files/MakeMyTrip.xlsx"); // A file object is created pointing to the Excel file "./Files/MakeMyTrip.xlsx"
        inputStream = new FileInputStream(file); // A FileInputStream is created to read the Excel file.
        wb = new XSSFWorkbook(inputStream);
        sheet = wb.getSheet(sheetName);  // Retrieves the sheet with the given sheetName from the workbook.
        closeResource(inputStream); // Close the input stream
    }

    public static void createRow(int i) {
        row = sheet.createRow(i); // Create a new row at the specified index in the sheet
    }
    
    public static void setHeadingForCabs(String hName, String hPrice) {
        row.createCell(0).setCellValue(hName); // Set the heading for the car name in the 1st cell of the row
        row.createCell(1).setCellValue(hPrice); // Set the heading for the car price in the 2nd cell of the row
    }

    public static void setValue(String cabName, String price) {
        row.createCell(0).setCellValue(cabName); // Set the value for the car name in the 1st cell of the row
        row.createCell(1).setCellValue(price); // Set the value for the car price in the 2nd cell of the row
    }
    
    public static void setHeadingForError(String errName) {
        row.createCell(0).setCellValue(errName); // Set the heading for the error name in the 1st cell of the row
    }
    
    public static void setErrorMsg(String errMsg) {
    	row.createCell(0).setCellValue(errMsg); // Set the error message in the 1st cell of the row
    }
    
    public static void setAdultCount(List<String> count) {
        int rowNum = 0; // Initialize the row number to 0
        for (String countValue : count) { // Iterate over each value in the count list
            row = sheet.getRow(rowNum); // Retrieve the existing row
            if (row == null) {
                row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
            }
            row.createCell(0).setCellValue(countValue); // Set the cell value in the 1st column of the current row to the count value
            rowNum++; // Increment the row number for the next iteration
        }
    }

    private static void closeResource(Closeable resource) {
        // Check if the resource is not null
        if (resource != null) {
            try {
                // Close the resource
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
