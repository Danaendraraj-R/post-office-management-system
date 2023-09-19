import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login1")
public class Login1 extends HttpServlet {

	private static final long serialVersionUID = 1L;	 

	public Login1() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && username.equals("Admin") && password != null && password.equals("Admin")) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("username", username);
			request.getRequestDispatcher("AdminDash.jsp").forward(request, response);
		}
               else
               {
              response.sendRedirect("Error2.jsp");
                }
	}
}
