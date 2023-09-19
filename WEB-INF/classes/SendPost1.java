import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList; 
@WebServlet("/SendPost1")
@SuppressWarnings("unchecked")
public class SendPost1 extends HttpServlet {
  
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int Unum;

PrintWriter out = response.getWriter();
Unum=ThreadLocalRandom.current().nextInt(1000000,5000000);
String u9=String.valueOf(Unum);
int rp,sp;
String u10;
        ArrayList std=new ArrayList();
        // Jdbc Connection
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
            System.out.println("connection successful");
              
            PreparedStatement st = conn.prepareStatement("INSERT INTO POSTDETAILS(SENDERNAME,SENDERADDRESS,SENDERPINCODE,SENDERPHONENUMBER,RECIEVERNAME,RECIEVERADDRESS,RECIEVERPINCODE,RECIEVERPHONENUMBER,CONSIGNMENTNO,PRICE) values(?, ?, ?, ?, ?, ? , ? ,?, ? ,?)");
  
            // set values into the query
            st.setString(1, request.getParameter("u1"));
            st.setString(2, request.getParameter("u2"));
            st.setString(3, request.getParameter("u3"));
            st.setString(4, request.getParameter("u4"));
	    st.setString(5, request.getParameter("u5"));
	    st.setString(6, request.getParameter("u6"));
            st.setString(7, request.getParameter("u7"));
	    st.setString(8, request.getParameter("u8"));
            st.setString(9, u9 );
            int min;
  	    int snd=Integer.parseInt(request.getParameter("u3"));
  	    int rvr=Integer.parseInt(request.getParameter("u7"));
            if(snd>rvr)
            min=snd-rvr;
            else
            min=rvr-snd;
            if(min<200)
            rp=50;
            else if(min==200||min<500)
            rp=100;
            else
            rp=150;
            u10=String.valueOf(rp);
            st.setString(10, u10 );
            // Execute the query
            st.executeUpdate();
  
            	        std.add(request.getParameter("u1"));
			std.add(request.getParameter("u2"));
			std.add(request.getParameter("u3"));
			std.add(request.getParameter("u4"));
			std.add(request.getParameter("u5"));
			std.add(request.getParameter("u6"));
			std.add(request.getParameter("u7"));
			std.add(request.getParameter("u8"));
			std.add(u9);
			std.add(u10);
			
            st.close();
            conn.close();
  
			request.setAttribute("data",std);
			RequestDispatcher rd=request.getRequestDispatcher("Post.jsp");
			rd.forward(request,response);
        } catch (Exception e) {
            out.println(e);
        }
    }
}