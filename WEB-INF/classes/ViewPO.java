import java.io.*;  
import jakarta.servlet.*;  
import jakarta.servlet.http.*;  
import java.sql.*;  
    
public class ViewPO extends HttpServlet  
{    
     public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
      { 
         PrintWriter out = res.getWriter();  
         res.setContentType("text/html");  
	 String tablename="podetails";
	// ArrayList al = new ArrayList();
	// ArrayList list = new ArrayList();
         try 
         {   
             Class.forName("org.postgresql.Driver");  
             Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres","Rajdr039*");    
             Statement stmt = con.createStatement();
             String str="select * from "+tablename;  
             ResultSet rs = stmt.executeQuery(str);
             HttpSession session = req.getSession();
             
             session.setAttribute("query",str);
             out.println("<html><head><title>View Post Office</title>");
             out.println("<link rel='stylesheet' href='/styles.css'>");
	     out.println("<style>th{padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #C04000;color: white;}");
	     out.println("th {border: 1px solid #ddd;padding: 8px;}table{font-family: Arial, Helvetica, sans-serif;border-collapse: collapse;width: 100%;}");
	     out.println("td{background-color:#fbfce8;border: 1px solid #ddd;padding: 8px;},a:link { text-decoration: none; },th {border: 1px solid #ddd;padding: 8px;}tr:hover {background-color: #ddd;}");
	     out.println("input[type=submit],button,a{background-color:#C04000;border-radius: 56px;color: #fff;font-family: system-ui,-apple-system,system-ui,'Segoe UI',Roboto,Ubuntu,'Helvetica Neue',sans-serif;");
	     out.println("font-size: 18px;font-weight: 600;outline: 0;padding: 16px 21px;position: relative;text-align: center;}</style>");
	     out.println("<script type='text/javascript' src='https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js'></script>");
	     out.println("<script type='text/javascript' src='Exportxl.js'></script>");
	     out.println("<script type='text/javascript' src='ExportPDF.js'></script>");
             out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js'></script");
	     out.println("</head><body>");
	     out.println("<form action=ExportFiles method='get'><input type='hidden' value= ");
             out.println(str);
             out.println("name='query'/><input type='hidden' value='pdf' name='type'/><input type='hidden' value='podetails' name='source'/><input type='submit' value='Export to PDF'/></form>");
	     out.println("<form action=ExportFiles method='get'><input type='hidden' value= ");
	     out.println(str);
	     out.println("name='query'/><input type='hidden' value='xls' name='type'/><input type='hidden' value='podetails' name='source'/><input type='submit' value='Export to Excel'/></form>");
		out.println("<br><br>");		 
         out.println("<div id='POList'> <table id='POlist' border=1 width=100% height=50%>");
		 out.println("<thead><tr><th>POID</th><th>PONAME</th><th>ADDRESS</th><th>PINCODE</th><th>CITY</th><th>STATE</th><th>TELEPHONE</th><th>CREATEDTIME</th><th>MODIFIEDTIME</th><th>Update</th><th>Delete</th></tr></thead><tbody>");  
             while (rs.next()) 
             {   
                    String poid=rs.getString("uniqueid");
					String poname=rs.getString("poname");
                           
					String address=rs.getString("address");

					String pincode=rs.getString("pincode");

					String city=rs.getString("city");
			
					String state=rs.getString("state");

					String telephone=rs.getString("telephone");

					String createdtime=rs.getString("createdtime");

					String modifiedtime=rs.getString("modifiedtime");

                out.println("<tr><td>" + poid + "</td><td>" + poname + "</td><td>" + address + "</td><td>" + pincode + "</td><td>" + city + "</td><td>" + state + "</td><td>" + telephone + "</td><td>" + createdtime + "</td><td>" + modifiedtime + "</td></div><td><form action='UpdatePO.jsp' method='post'><input type='hidden' name='poid' value='"+poid+"'/><input type='submit' value='Update'/></form></td><td><form action='DeletePO' method='post'><input type='hidden' name='poid' value='"+poid+"'/><input type='submit' value='Delete'/></form></td></tr>");   
             }  
             out.println("</tbody></table></div>");
             out.println("<center><form action='AdminDash.jsp' method='post'><input type='submit' value='Back'/></form><center>");
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
             out.println(e.getMessage()); 
} 
     }  
 }
