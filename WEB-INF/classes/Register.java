import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
  
@WebServlet("/Register")
public class Register extends HttpServlet {
  
    private static final long serialVersionUID = 1L;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          PrintWriter out = response.getWriter();
        // Jdbc Connection
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
            System.out.println("connection successful");
              
            PreparedStatement st = conn.prepareStatement("insert into userdetails(USERNAME,PASSWORD,NAME,ADDRESS,PINCODE,PHONENUMBER) values(?, ?, ?, ?, ?, ?)");
  
            // set values into the query
            st.setString(1,request.getParameter("Username"));
            st.setString(2, request.getParameter("Password"));
            st.setString(3, request.getParameter("Name"));
            st.setString(4, request.getParameter("Address"));
	    st.setString(5, request.getParameter("Pincode"));
	    st.setString(6, request.getParameter("Phonenumber"));  
            // Execute the query
            st.executeUpdate();
  

            st.close();
            conn.close();

        response.sendRedirect("AdminLogin.jsp?msg=Registered Successfully");
  
        } catch (Exception e) {
            out.println(e);
        }
    }
}