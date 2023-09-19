<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
try {
Class.forName("org.postgresql.Driver");
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN","http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Post Office</title>
<style>
input[type=submit]{  
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
margin-top:200px;
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

td, th {
    border: 1px solid #777;
    padding: 0.5rem;
    text-align: center;
}
tr{
background-color:"white";
}
div{
	background-color:#FFCE80;
}	
table {
    border-collapse: collapse;
	align:center;
	background-color: #cfcfcf;
}
caption {
    font-size: 0.8rem;
}
id{
	background:"red";
}
</style>
</head>
<body>
<center><h1> List of Registered PO</h1>
<table border="2">
<div>
<tr name="id">
<td>POID</td>
<td>PONAME</td>
<td>ADDRESS</td>
<td>PINCODE</td>
<td>CITY</td>
<td>STATE</td>
<td>TELEPHOPNE NUMBER</td>
<td>CREATED TIME</td>
<td>MODIFIED TIME</td>
</tr>
</div>
<%
try{
connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library","postgres", "Rajdr039*");
statement=connection.createStatement();
String sql ="select * from podetails";
rs = statement.executeQuery(sql);
while(rs.next()){
%>
 <tr>
    <td><%=rs.getString("uniqueid") %></td>
    <td><%=rs.getString("poname") %></td>
    <td><%=rs.getString("address") %></td>
    <td><%=rs.getString("pincode") %></td>
    <td><%=rs.getString("city") %></td>
    <td><%=rs.getString("state") %></td>
    <td><%=rs.getString("telephone") %></td>
    <td><%=rs.getString("createdtime") %></td>
    <td><%=rs.getString("modifiedtime") %></td>
</tr> 
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</center>
</body>
</html> 