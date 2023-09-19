package org.Exportmodules;

import java.util.*;
import java.io.PrintWriter;

import java.io.*;
import java.sql.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

import org.Exportmodules.PDF;
import org.Exportmodules.XLS;
public class Exportmodules
{
           PDF a=new PDF();
	   XLS b=new XLS();

public ByteArrayOutputStream CreateFiles(ResultSet rs,String type)
{
ByteArrayOutputStream baos=new ByteArrayOutputStream();
if(type.equals("pdf"))
{
baos =a.CreateFile(rs);
}
else if(type.equals("xls"))
{
baos =b.CreateFile(rs);
}
else
{
String text="Format not Available";
for (int i = 0; i < text.length(); ++i)
    baos.write(text.charAt(i));
}
return baos;
}
}


