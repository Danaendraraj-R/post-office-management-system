import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet("/Login")
@SuppressWarnings("unchecked")
public class Login extends HttpServlet {
	public void doPost(HttpServletRequest req , HttpServletResponse response) throws IOException{
					PrintWriter out = response.getWriter();
					String username="username";
					String password="password";
					ArrayList std=new ArrayList();
					String name="name";
					String address="address";
					String pincode="pincode";
					String phonenumber="phonenumber";
					HttpServletRequest request;
		try{
			
			String user = req.getParameter("username");
			String pass = req.getParameter("password");
			
			

		Class.forName("org.postgresql.Driver");
		Connection conn = null;
            Statement stmt = null;
         conn = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "Rajdr039*");
			int allow = 0;
			stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERDETAILS;");
            while(rs.next()){
                username = rs.getString("username");
				password = rs.getString("password");
                if(username.equals(user) && password.equals(pass)){
                    allow++;
				name = rs.getString("name");
			    address = rs.getString("address");
			    pincode = rs.getString("pincode");
				phonenumber = rs.getString("phonenumber");
				break;
                }
            }
            rs.close();
            stmt.close();
			conn.close();
			if(allow==0)
			{
			response.sendRedirect("Error.jsp");
			}
			else
			{
			std.add(username);
			std.add(password);
			std.add(name);
			std.add(address);
			std.add(pincode);
			std.add(phonenumber);
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("username", user);
			httpsession.setAttribute("data",std);
			RequestDispatcher rd=req.getRequestDispatcher("User.jsp");
			rd.forward(req,response);
            }
			
		} catch(Exception e){
			out.println(e);
	    }
	}
}