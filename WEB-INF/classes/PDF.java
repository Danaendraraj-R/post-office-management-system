package org.Exportmodules;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.sql.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.Exportmodules.Exportmodule;
import org.Exportmodules.Modifydata;

public class PDF extends Exportmodule {

public ByteArrayOutputStream CreateFile(ResultSet rs) { 
   
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
       
        try
        {  
            Document document = new Document();
            ResultSetMetaData resultsetmetadata = rs.getMetaData();
            Modifydata md=new Modifydata();
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
                String s1;
                   
                while(k < i + 1) 
                {
               
	       int columnType = resultsetmetadata.getColumnType(k);
	       switch (columnType) {
               case Types.INTEGER:
                   s1=Integer.toString(rs.getInt(resultsetmetadata.getColumnName(k)));
                   break;
               case Types.VARCHAR:
                   s1=rs.getString(resultsetmetadata.getColumnName(k));
                   break;
               case Types.BIT:
                   s1=Boolean.toString(rs.getBoolean(resultsetmetadata.getColumnName(k)));
                   break;
               default:
                   s1=null;
                   System.out.print("Unsupported data type");
           }


                    String s2=md.modifydata(resultsetmetadata.getColumnName(k),s1);
                    PdfPCell pdfpcell1 = new PdfPCell(new Phrase(s2, font));
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
            System.out.println("Exception from pdf");
        }


        return bout;
}
}