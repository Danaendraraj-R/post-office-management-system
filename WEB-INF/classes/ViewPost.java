import java.io.*;  
import jakarta.servlet.*;  
import jakarta.servlet.http.*;  
import java.sql.*;
import java.sql.ParameterMetaData;
    
public class ViewPost extends HttpServlet  
{    
     public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException 
      {  
         String phonenumber=(String)req.getParameter("phonenumber");
         PrintWriter out = res.getWriter();  
         res.setContentType("text/html");  
         out.println("<html><body>");  
         try 
         {  
             Class.forName("org.postgresql.Driver");  
             Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");    
             Statement stmt = con.createStatement(); 
             PreparedStatement stmt1=con.prepareStatement("select * from postdetails where senderphonenumber=?");
             stmt1.setString(1,phonenumber);
             String str=stmt1.toString(); 
             ResultSet rs = stmt1.executeQuery(); 
             HttpSession session = req.getSession();
	     session.setAttribute("query",str);
             out.println("<html><head><title>View Post</title>");
             out.println("<link rel='stylesheet' href='/styles.css'>");
	     out.println("<style>th{padding-top: 12px;padding-bottom: 12px;text-align: center;background-color: #C04000;color: white;}");
	     out.println("th {border: 1px solid #ddd;padding: 8px;}table{font-family: Arial, Helvetica, sans-serif;border-collapse: collapse;width: 100%;}");
	     out.println("td{background-color:#fbfce8;border: 1px solid #ddd;padding: 8px;},th {border: 1px solid #ddd;padding: 8px;}tr:hover {background-color: #ddd;}");
	     out.println("a,button,input[type=submit]{background-color:#C04000;border-radius: 56px;color: #fff;font-family: system-ui,-apple-system,system-ui,'Segoe UI',Roboto,Ubuntu,'Helvetica Neue',sans-serif;");
	     out.println("font-size: 18px;font-weight: 600;outline: 0;padding: 16px 21px;position: relative;text-align: center;}</style>");
	     out.println("<script type='text/javascript' src='https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js'></script>");
             out.println("<script type='text/javascript' src='Exportxl.js'></script>");
	     out.println("<script type='text/javascript' src='ExportPDF.js'></script>");
             out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js'></script");
	     out.println("</head><body>");
             out.println("<form action=ExportFiles method='get'><input type='hidden' value='xls' name='type'/><input type='hidden' value=");
             out.println(stmt1);
             out.println("name='query'/><input type='hidden' value='postdetails' name='source'/><input type='submit' value='Export to Excel'/></form>");
             out.println("<form action=ExportFiles method='get'><input type='hidden' value='pdf' name='type'/><input type='hidden' value=");
	     out.println(stmt1);
	     out.println("name='query'/><input type='hidden' value='postdetails' name='source'/><input type='submit' value='Export to PDF'/></form>");
	     out.println("<div id='postlist'><table id='SentPost' border=1 width=100% height=50%>");  
             out.println("<thead><tr><th>Sender Name</th><th>Sender Address</th><th>Sender Pincode</th><th>Sender Phone</th><th>Receiver Name</th><th>Reciever Address</th><th>Reciever Pincode</th><th>Reciever Phonenumber</th><th>Consignment Number</th><th>Price</th></tr></thead><tbody>");

             while (rs.next()) 
             {   
                    String sn=rs.getString("sendername");
					String sa=rs.getString("senderaddress");
					String sp=rs.getString("senderpincode");
					String spn=rs.getString("senderphonenumber");
					String rn=rs.getString("recievername");
					String ra=rs.getString("recieveraddress");
					String rp=rs.getString("recieverpincode");
					String rpn=rs.getString("recieverphonenumber");
					String cn=rs.getString("consignmentno");
					String price=rs.getString("price");
                out.println("<tr><td>" + sn + "</td><td>" + sa + "</td><td>" + sp + "</td><td>" + spn + "</td><td>" + rn + "</td><td>" + ra + "</td><td>" + rp + "</td><td>" + rpn + "</td><td>" + cn + "</td><td>" +price+ "</td></tr>");   
             } 
              
             out.println("</tbody></table></div>");
             out.println("<br>");
             out.println("<center><a href='User.jsp'>Back</a><center>");
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
             out.println(e.getMessage()); 
} 
     } 

 }
