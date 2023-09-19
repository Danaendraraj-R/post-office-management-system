package org.Exportmodules;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;
import java.io.PrintWriter;
import java.io.*;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class XLS extends Exportmodule {
    public XLS()
	{
		
	}

 public ByteArrayOutputStream CreateFile(ResultSet rs) {
   
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
       
	  HSSFWorkbook workbook = new HSSFWorkbook(); 
      HSSFSheet spreadsheet = workbook.createSheet("podetails");
      Modifydata md=new Modifydata();
      try{
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
    String s1=null;
    while(rs.next()) {
    row = spreadsheet.createRow(k);
         l=0;
         for (int m = 1; m < numberOfColumns + 1; m++){ 
         cell = row.createCell(l);
	     int columnType = rsMetaData.getColumnType(m);
	       switch (columnType) {
               case Types.INTEGER:
                   s1=Integer.toString(rs.getInt(rsMetaData.getColumnName(m)));
                   break;
               case Types.VARCHAR:
                   s1=rs.getString(rsMetaData.getColumnName(m));
                   break;
               case Types.BIT:
                   s1=Boolean.toString(rs.getBoolean(rsMetaData.getColumnName(m)));
                   break;
               default:
                   s1=null;
                   System.out.print("Unsupported data type");
           }
        
         String s2=md.modifydata(rsMetaData.getColumnName(m),s1);
         cell.setCellValue(s2);
         l++;
      }
      k++;
      }

workbook.write(bout);
      }       
	  catch(Exception e){
	  e.printStackTrace();
          System.out.println("Exception from XLS");
	  }
        return bout;
    }
}	