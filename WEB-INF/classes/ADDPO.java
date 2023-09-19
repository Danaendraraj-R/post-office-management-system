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
  
@WebServlet("/ADDPO")
public class ADDPO extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PrintWriter out = response.getWriter();
       String a,b;
       DateFormat dateformat=new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
       Date h = Calendar.getInstance().getTime();
       Date i = Calendar.getInstance().getTime();
       a=dateformat.format(h);
       b=dateformat.format(i);
       String htmlRespone;

        // Jdbc Connection
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
            System.out.println("connection successful");
              
            PreparedStatement st = conn.prepareStatement("insert into podetails(UNIQUEID,PONAME,ADDRESS,PINCODE,CITY,STATE,TELEPHONE,CREATEDTIME,MODIFIEDTIME) values(?, ?, ?, ?, ?, ?, ? ,? ,?)");
  
            // set values into the query
            st.setString(1,request.getParameter("poid"));
            st.setString(2, request.getParameter("poname"));
            st.setString(3, request.getParameter("address"));
            st.setString(4, request.getParameter("pincode"));
	    st.setString(5, request.getParameter("city"));
	    st.setString(6, request.getParameter("state"));  
	    st.setString(7, request.getParameter("telephone"));
	    st.setString(8, a);
	    st.setString(9, b);
            // Execute the query
            st.executeUpdate();
  
            response.sendRedirect("AdminDash.jsp?msg=Added Successfully");
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
        }
    }
}