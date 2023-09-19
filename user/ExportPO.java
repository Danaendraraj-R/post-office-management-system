 
import java.io.*;
import java.sql.*;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

 
public class ExportPO {
 
    public static void main(String[] args) {
     
        String excelFilePath = "Reviews-export.xlsx";
         
        try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
            String sql = "SELECT * FROM podetails";
 
            Statement statement = connection.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("POList");
 
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
			
			
 
            Row headerRow = sheet.createRow(0);
 
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("PO ID");
 
            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("PO Name");
 
            headerCell = headerRow.createCell(2);
            headerCell.setCellValue("Address");
 
            headerCell = headerRow.createCell(3);
            headerCell.setCellValue("Pincode");
 
            headerCell = headerRow.createCell(4);
            headerCell.setCellValue("City");
		
		    headerCell = headerRow.createCell(5);
            headerCell.setCellValue("State");   
		
            headerCell = headerRow.createCell(6);
            headerCell.setCellValue("Telephone");
		
            headerCell = headerRow.createCell(7);
            headerCell.setCellValue("Created Time");
 
            headerCell = headerRow.createCell(8);
            headerCell.setCellValue("Modified Time");

 
			        int rowCount = 1;
			
			        while (result.next()) {
            String poid = result.getString("uniqueid");
            String poname = result.getString("poname");
			String address = result.getString("address");
			String city = result.getString("city");
			String state = result.getString("state");
			String pincode = result.getString("pincode");
			String phnnumber = result.getString("telephone");
			String createdtime = result.getString("createdtime");
            String modifiedtime = result.getString("modifiedtime");
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(poid);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(poname);
			
			cell = row.createCell(columnCount++);
            cell.setCellValue(address);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(city);
			
			cell = row.createCell(columnCount++);
            cell.setCellValue(state);
			
			cell = row.createCell(columnCount++);
            cell.setCellValue(pincode);
			
			cell = row.createCell(columnCount++);
            cell.setCellValue(createdtime);
			
            cell = row.createCell(columnCount++);
            cell.setCellValue(modifiedtime);

 
 
            statement.close();
 
        }

		}
		catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
 
 
}
}