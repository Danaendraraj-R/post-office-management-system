import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import org.Exportmodules.Exportmodule;
import org.Exportmodules.Exportmodules;
import org.Exportmodules.PDF;
import org.Exportmodules.XLS;
import org.Exportmodules.Modifydata;
import org.Exportmodules.Attendance;

import org.postgresql.jdbc.*;

public class ExportFiles extends HttpServlet {
    public ResultSet ResultSetWrapper(ResultSet resultSet) {
        return resultSet;
    }
public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try{
           HttpSession session = request.getSession();
           String tablename=request.getParameter("source");
           String type=request.getParameter("type");
           Exportmodules a=new Exportmodules();
	   Modifydata b=new Modifydata();
           Attendance ab=new Attendance();
           String str=(String)session.getAttribute("query");
           System.out.println(str);
          
	   Class.forName("org.postgresql.Driver");  
           Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres","Rajdr039*");
	   Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);    
           ResultSet source=stmt.executeQuery(str);
           //ResultSet rs=modifyResultSet(source,str);		
   	  // rs.beforeFirst();
           //stmt.executeUpdate("DROP TABLE T1");
           
           List<Map<String, String>> mapList = new ArrayList<>();
           String[] arr={"pincode","remote","status"};
           
           Map<String,String> map1 = new HashMap<>();
           map1.put("columnname", "remote");
	   map1.put("true", "Yes");
           map1.put("false","No");
	   mapList.add(map1);

	   Map<String,String> map2 = new HashMap<>();
	   map2.put("columnname","status");
	   map2.put("true","Successful");
           map2.put("false","In-transit");
	   mapList.add(map2);

           ab.setdata(mapList);

           response.setContentType("application;charset=UTF-8");
           response.addHeader("Content-Disposition", "attachment; filename=" + "List."+type);
           ServletOutputStream out = response.getOutputStream();
           b.setModifyString(arr);
	   ByteArrayOutputStream baos =a.CreateFiles(source,type);
           baos.writeTo(out);

}
catch(Exception e)
{
System.out.println(e);
System.out.println("Ex from export servlet");
}
}
 /*public ResultSet modifyResultSet(ResultSet rs,String q) throws SQLException {
      ResultSet modifiedRs=null;
      NTW1 a=new NTW1();

     Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres","Rajdr039*");
     Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     try {
    modifiedRs =ResultSetWrapper(rs);
    Class.forName("org.postgresql.Driver");
    String sql="CREATE TABLE T1 AS "+ q;    
    stmt.executeUpdate(sql);
    while (rs.next())
    {
          long str=Long.parseLong((rs.getString("pincode")));
          System.out.println((rs.getString("pincode")));
// converting number to words
          String pin=a.convert(str);
          System.out.println(pin);
          PreparedStatement stmt1=con.prepareStatement("UPDATE T1 SET PINCODE = ? WHERE UNIQUEID = ?;");
          stmt1.setString(1,pin);
          stmt1.setString(2,rs.getString("uniqueid"));
          stmt1.executeUpdate();
    }
modifiedRs=stmt.executeQuery("select * from T1");
} 
catch (Exception e) {
  System.out.println(e);
  modifiedRs=stmt.executeQuery("select * from T1");
}
return modifiedRs;

}*/
}

