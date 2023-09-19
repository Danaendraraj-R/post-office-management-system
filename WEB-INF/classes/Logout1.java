import java.io.IOException;  
import java.io.PrintWriter;  
  
import jakarta.servlet.ServletException;  
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;  
import jakarta.servlet.http.HttpServletResponse;  
import jakarta.servlet.http.HttpSession;  
public class Logout1 extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            request.getRequestDispatcher("Login.jsp").include(request, response);  
              
            HttpSession session=request.getSession();  
            session.invalidate();  
              
            response.sendRedirect("Login.jsp");  
              
            out.close();  
    }  
}  