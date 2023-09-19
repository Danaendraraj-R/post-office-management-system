import java.io.File;
import java.io.FileOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exportdata {
   public static void main(String[] args) throws Exception {
      Class.forName("org.postgresql.Driver");
      Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
      
      Statement statement = connect.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from podetails");
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("podetails");
      
      XSSFRow row = spreadsheet.createRow(1);
      XSSFCell cell;
      cell = row.createCell(0);
      cell.setCellValue("PO ID");
      cell = row.createCell(1);
      cell.setCellValue("PO NAME");
      cell = row.createCell(2);
      cell.setCellValue("Address");
      cell = row.createCell(3);
      cell.setCellValue("City");
      cell = row.createCell(4);
      cell.setCellValue("State");
	  cell = row.createCell(5);
      cell.setCellValue("Pincode");
	  cell = row.createCell(6);
      cell.setCellValue("Telephone");
	  cell = row.createCell(7);
      cell.setCellValue("Created Time");
	  cell = row.createCell(8);
      cell.setCellValue("Modified Time");
      int i = 1;

      while(resultSet.next()) {
         row = spreadsheet.createRow(i);
         cell = row.createCell(0);
         cell.setCellValue(resultSet.getInt("uniqueid"));
         cell = row.createCell(1);
         cell.setCellValue(resultSet.getString("poname"));
         cell = row.createCell(2);
         cell.setCellValue(resultSet.getString("address"));
         cell = row.createCell(3);
         cell.setCellValue(resultSet.getString("city"));
         cell = row.createCell(4);
         cell.setCellValue(resultSet.getString("state"));
		 cell = row.createCell(5);
         cell.setCellValue(resultSet.getString("pincode"));
		 cell = row.createCell(6);
         cell.setCellValue(resultSet.getString("telephone"));
		 cell = row.createCell(7);
         cell.setCellValue(resultSet.getString("createdtime"));
		 cell = row.createCell(8);
         cell.setCellValue(resultSet.getString("modifiedtime"));
         i++;
      }

      FileOutputStream out = new FileOutputStream(new File("exceldatabase.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("exceldatabase.xlsx written successfully");
   }
}