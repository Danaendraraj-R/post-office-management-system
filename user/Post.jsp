<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Post Office Management System</title>
<style>
a{  
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
    </head>
 
    <body>
<center>
<% ArrayList std=(ArrayList)request.getAttribute("data"); %>
<h1>Post Bill</h1>
<table style="with: 50%" align="center">
<tr>
<td>Sender Name</td>
<td><%=std.get(0)%></td>
</tr>
<tr>
<td>Sender Address</td>
<td><%=std.get(1)%></td>
</tr>
<td>Sender Pincode</td>
<td><%=std.get(2)%></td>
</tr>
<tr>
<td>Sender Phonenumber</td>
<td><%=std.get(3)%></td>
</tr>
<tr>
<td>Reciever Name</td>
<td><%=std.get(4)%></td>
</tr>
<tr>
<td>Reciever Address</td>
<td><%=std.get(5)%></td>
</tr>
<tr>
<td>Reciever Pincode</td>
<td><%=std.get(6)%></td>
</tr>
<tr>
<td>Reciever Phonenumber</td>
<td><%=std.get(7)%></td>
</tr>
<tr>
<td>Consignment Number</td>
<td><%=std.get(8)%></td>
</tr>
<tr>
<td>Price</td>
<td><%=std.get(9)%></td>
</tr>
</table>
<a href="User.jsp">Back</a>
</center>
</body> 
</html>