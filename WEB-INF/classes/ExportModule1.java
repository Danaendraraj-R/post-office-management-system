package org.ExportModule1;

import java.io.FileOutputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.*;

public abstract class ExportModule1{
    public abstract ByteArrayOutputStream exportTable(Connection connection, String query);
    ByteArrayOutputStream baos=new ByteArrayOutputStream();
    public ExportModule1 getExporter(String format) {
        switch (format) {
            case "pdf":
                return new PDF();
            case "xls":
                return new XLS();
            default:
                return new Unavailable();
        }
    }
}

class XLS extends ExportModule1{

    public ByteArrayOutputStream exportTable(Connection connection, String query) {
              ByteArrayOutputStream bout = new ByteArrayOutputStream();
      
      HSSFWorkbook workbook = new HSSFWorkbook(); 
      HSSFSheet spreadsheet = workbook.createSheet("podetails");
      try{
      
      Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      ResultSet rs=stmt.executeQuery(query); 
      ResultSetMetaData rsMetaData=rs.getMetaData();
      int numberOfColumns = rsMetaData.getColumnCount();
      HSSFRow row = spreadsheet.createRow(0);
      HSSFCell cell;
      int j=0;
       for (int i = 1; i < numberOfColumns + 1; i++) {
      cell = row.createCell(j);
      cell.setCellValue(rsMetaData.getColumnName(i));
      j++;
    }
    int k=1;
    int l=0;
    while(rs.next()) {
    row = spreadsheet.createRow(k);
         l=0;
         for (int m = 1; m < numberOfColumns + 1; m++){ 
         cell = row.createCell(l);
         cell.setCellValue(rs.getString(rsMetaData.getColumnName(m)));
         l++;
      }
      k++;
      }
workbook.write(bout);
       }
	  catch(Exception e){
	  System.out.println(e);
	  }
        return bout;  
    }
}

class PDF extends ExportModule1 {
    public ByteArrayOutputStream exportTable(Connection connection, String query) {
               ByteArrayOutputStream bout = new ByteArrayOutputStream();
        
        try
        {  
            
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=stmt.executeQuery(query);
            Document document = new Document();
            ResultSetMetaData resultsetmetadata = rs.getMetaData();
            int i = resultsetmetadata.getColumnCount();
            PdfPTable pdfptable = new PdfPTable(i);
            pdfptable.setWidthPercentage(100F);
            BaseFont basefont = BaseFont.createFont("Times-Roman", "Cp1252", true);
            Font font = new Font(basefont, 8F);
            for(int j = 1; j < i + 1; j++)
            {
                PdfPCell pdfpcell = new PdfPCell(new Phrase(resultsetmetadata.getColumnName(j), font));
                pdfptable.addCell(pdfpcell);
            }

            while(rs.next()) 
            {
                int k = 1;
                while(k < i + 1) 
                {
                    String s1 = rs.getString(resultsetmetadata.getColumnName(k));
                    PdfPCell pdfpcell1 = new PdfPCell(new Phrase(s1, font));
                    pdfptable.addCell(pdfpcell1);
                    k++;
                }

}
            PdfWriter.getInstance(document, bout);
            document.open();
            document.add(pdfptable);
	    document.close();
}


        catch (Exception ex) {

            System.out.println(ex);
        }


        return bout;
    }

}
class Unavailable extends ExportModule1
{
 public ByteArrayOutputStream exportTable(Connection connection, String query) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      String text="Format not Available";
      for (int i = 0; i < text.length(); ++i)
      baos.write(text.charAt(i));
      return baos; 
 }  
}


