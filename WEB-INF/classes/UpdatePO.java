import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;
import static java.lang.System.out;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
  
@WebServlet("/UpdatedPO")
public class UpdatePO extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
				
      Connection c = null;
      Statement stmt = null;
       PrintWriter out = response.getWriter();
      String str=request.getParameter("poid");
      String newstr=request.getParameter("Newdata");
      String m=request.getParameter("options");
	  int n=Integer.parseInt(m);
      int a=0;
      String dt;
	  String htmlRespone;
      DateFormat dateformat=new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
      PreparedStatement sql,sql1;
      Date date = Calendar.getInstance().getTime();  
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "Rajdr039*");
         c.setAutoCommit(false); 
	 switch(n)
         {
	 case 1:
         sql=c.prepareStatement("UPDATE PODETAILS SET UNIQUEID = ? WHERE UNIQUEID = ?;");			
	 sql.setString(1,newstr);
	 sql.setString(3,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	response.sendRedirect("Error1.jsp");
	 break;
	 case 2:
         sql=c.prepareStatement("UPDATE PODETAILS SET PONAME = ? WHERE UNIQUEID = ?;");		
	 sql.setString(1,newstr);
	 sql.setString(2,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	 response.sendRedirect("Error1.jsp");
	 break;
	 case 3:
         sql=c.prepareStatement("UPDATE PODETAILS SET ADDRESS = ? WHERE UNIQUEID = ?;");			
	 sql.setString(1,newstr);
	 sql.setString(2,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	response.sendRedirect("Error1.jsp");
	 break;
	 case 4:
         sql=c.prepareStatement("UPDATE PODETAILS SET PINCODE = ? WHERE UNIQUEID = ?;");		
	 sql.setString(1,newstr);
	 sql.setString(2,str);        
	 if(sql.executeUpdate()==1)
	 a++;
         else
	 response.sendRedirect("Error1.jsp");
	 break;
	 case 5:
         sql=c.prepareStatement("UPDATE PODETAILS SET CITY = ? WHERE UNIQUEID = ?;");			
	 sql.setString(1,newstr);
	 sql.setString(2,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	 response.sendRedirect("Error1.jsp");
	 break;
	 case 6:
         sql=c.prepareStatement("UPDATE PODETAILS SET STATE = ? WHERE UNIQUEID = ?;");			
	 sql.setString(1,newstr);
	 sql.setString(2,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	 response.sendRedirect("Error1.jsp");
	 break;
	 case 7:
         sql=c.prepareStatement("UPDATE PODETAILS SET TELEPHONE = ? WHERE UNIQUEID = ?;");		
	 sql.setString(1,newstr);
	 sql.setString(2,str);
         if(sql.executeUpdate()==1)
	 a++;
         else
	 response.sendRedirect("Error1.jsp");
	 break;
	 default:
         response.sendRedirect("Error1.jsp");
         }; 
	 sql1=c.prepareStatement("UPDATE PODETAILS SET MODIFIEDTIME = ? WHERE UNIQUEID = ?;");
         dt=dateformat.format(date);
	 sql1.setString(1,dt);	
	 sql1.setString(2,str);
         if((sql1.executeUpdate()==1)&&(a==1))
	 response.sendRedirect("Popup.html?msg=Updated Successfully");
         else
	 response.sendRedirect("Error1.jsp");
         c.commit();
         c.close();
      } catch ( Exception e ) {
         out.println(e);
      }
}
}