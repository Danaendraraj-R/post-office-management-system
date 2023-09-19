<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>	
<!DOCTYPE html>
<html>
<head>
<title>Post Office Management System</title>
<%String inf=(String)request.getRemoteUser();%>
<%ArrayList<String> std=new ArrayList<String>();%>
<%
try {
Class.forName("org.postgresql.Driver");
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
try{
connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
statement=connection.createStatement();
String sql ="select * from userdetails";
rs = statement.executeQuery(sql);
while(rs.next()){
	String name=rs.getString("username");
	if(name.equals(inf))
	{
	std.add(rs.getString("username"));
	std.add(rs.getString("password"));
	std.add(rs.getString("name"));
	std.add(rs.getString("address"));
	std.add(rs.getString("pincode"));
	std.add(rs.getString("phonenumber"));
	break;
	}
}
connection.close();
} 
catch(Exception e) 
{
System.out.println(e);
}
System.out.println(std);
%>
<style>
a,input{  
  background-color:#C04000;
  border: 0;
  border-radius: 56px;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-family: system-ui,-apple-system,system-ui,"Segoe UI",Roboto,Ubuntu,"Helvetica Neue",sans-serif;
  font-size: 18px;
  font-weight: 600;
  outline: 0;
  padding: 16px 21px;
  position: relative;
  text-align: center;
  text-decoration: none;
  transition: all .3s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}
body { 
background-color: #cfcfcf; margin-top: 0.0em;
background-image:url(PO2.jpg);
background-repeat: no-repeat;
background-size:cover;
background-position: center center;
background-attachment: fixed;
margin-top:250px;
}
h1{
 font-family: Impact, Charcoal, sans-serif;
font-size: 70px;
letter-spacing: 0px;
word-spacing: 1.4px;
color: #FFCE80;
font-weight: 700;
text-decoration: none;
font-style: italic;
font-variant: small-caps;
text-transform: none;
text-shadow: 0 0 4px red
}
</style>
<meta http-equiv="refresh" />
    </head>
    <body>
<center>
        <h1>Login Successful</h1>
<h1>Hello! <%= std.get(2) %></h1>		
<form action="SendPost1.jsp" method="post">
<input type="hidden" name="u1" value="<%= std.get(2) %>"/>
<input type="hidden" name="u2" value="<%= std.get(3) %>" />
<input type="hidden" name="u3" value="<%= std.get(4) %>" />
<input type="hidden" name="u4" value= "<%= std.get(5) %>"/>
<input type="submit" id="details" value="Send Post with Register Details">
</form>
		<a href="SendPost2.jsp">Send Post without Registered Details</a>
		<form action=ViewPost method="get">
		<input type="hidden" name="phonenumber" value="<%= std.get(5) %>"/>
		<<input type="submit" value="View Sent Post">
		<a href="${pageContext.request.contextPath}/logout">Logout</a>

</form>
</center>
</body> 
</html>