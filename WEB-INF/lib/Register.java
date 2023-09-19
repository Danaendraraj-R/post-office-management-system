import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.json.simple.*; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
  
@WebServlet("/Register")
public class Register extends HttpServlet {
  
    private static final long serialVersionUID = 1L;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg;
        // Jdbc Connection
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
            System.out.println("connection successful");
              
            PreparedStatement st = conn.prepareStatement("insert into userdetails(USERID,PASSWORD,NAME,ADDRESS,PINCODE,PHONENUMBER) values(?, ?, ?, ?, ?, ?)");
  
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

        JSONArray list=new JSONArray();
	JSONObject obj=new JSONObject();
        msg="1";
        obj.put("msg",msg);
        list.add(obj);
	out.println(list.toJSONString());
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}