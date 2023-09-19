import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.PrintWriter;
@WebServlet("/Login")
@SuppressWarnings("unchecked")
public class GeneratePDF extends HttpServlet {
	public void doPost(HttpServletRequest req , HttpServletResponse response) throws IOException{
		
					try
					{
						    /* Create Connection objects */
                Class.forName ("org.postgresql.Driver"); 
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
                Statement stmt = conn.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("SELECT * FROM podetails");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("POList.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(9);
				my_report_table.setWidthPercentage(100f);
				BaseFont bf = BaseFont.createFont(
                        BaseFont.TIMES_ROMAN,
                        BaseFont.CP1252,
                        BaseFont.EMBEDDED);
                Font fontH1 = new Font(bf, 8);
 
				//my_report_table.setWidthPercent(90);
                //create a cell object
                PdfPCell table_cell;
                              
                                table_cell=new PdfPCell(new Phrase("POID",fontH1));
                                my_report_table.addCell(table_cell);

                                table_cell=new PdfPCell(new Phrase("POName",fontH1));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("Address",fontH1));
                                my_report_table.addCell(table_cell);
                           
                                table_cell=new PdfPCell(new Phrase("City",fontH1));
                                my_report_table.addCell(table_cell);

                                table_cell=new PdfPCell(new Phrase("State",fontH1));
                                my_report_table.addCell(table_cell);
			
                                table_cell=new PdfPCell(new Phrase("Pincode",fontH1));
                                my_report_table.addCell(table_cell);
					
                                table_cell=new PdfPCell(new Phrase("Telephone",fontH1));
                                my_report_table.addCell(table_cell);
						
                                table_cell=new PdfPCell(new Phrase("Createdtime",fontH1));
                                my_report_table.addCell(table_cell);
						
                                table_cell=new PdfPCell(new Phrase("Modifiedtime",fontH1));
                                my_report_table.addCell(table_cell);
                while (query_set.next()) {                
                                String poid = query_set.getString("uniqueid");
                                table_cell=new PdfPCell(new Phrase(poid,fontH1));
                                my_report_table.addCell(table_cell);
                                String poname=query_set.getString("poname");
                                table_cell=new PdfPCell(new Phrase(poname,fontH1));
                                my_report_table.addCell(table_cell);
                                String address=query_set.getString("address");
                                table_cell=new PdfPCell(new Phrase(address,fontH1));
                                my_report_table.addCell(table_cell);
                                String city=query_set.getString("city");
                                table_cell=new PdfPCell(new Phrase(city,fontH1));
                                my_report_table.addCell(table_cell);
								String state=query_set.getString("state");
                                table_cell=new PdfPCell(new Phrase(state,fontH1));
                                my_report_table.addCell(table_cell);
								String pincode=query_set.getString("pincode");
                                table_cell=new PdfPCell(new Phrase(pincode,fontH1));
                                my_report_table.addCell(table_cell);
								String telephone=query_set.getString("telephone");
                                table_cell=new PdfPCell(new Phrase(telephone,fontH1));
                                my_report_table.addCell(table_cell);
								String createdtime=query_set.getString("createdtime");
                                table_cell=new PdfPCell(new Phrase(createdtime,fontH1));
                                my_report_table.addCell(table_cell);
								String modifiedtime=query_set.getString("modifiedtime");
                                table_cell=new PdfPCell(new Phrase(modifiedtime,fontH1));
                                my_report_table.addCell(table_cell);
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                conn.close();               
            
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				
	}
	}