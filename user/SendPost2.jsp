<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Post Office Management System</title>
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
<h1>Send Post</h1>
<form action="SendPost1" method="post">
<table style="with: 50%" align="center">
<tr>
<td>Sender Name</td>
<td><input type="text" name="u1" /></td>
</tr>
<tr>
<td>Sender Address</td>
<td><input type="text" name="u2" /></td>
</tr>
<td>Sender Pincode</td>
<td><input type="text" name="u3" /></td>
</tr>
<tr>
<td>Sender Phonenumber</td>
<td><input type="text" name="u4" /></td>
</tr>
<tr>
<td>Reciever Name</td>
<td><input type="text" name="u5" /></td>
</tr>
<tr>
<td>Reciever Address</td>
<td><input type="text" name="u6" /></td>
</tr>
<td>Reciever Pincode</td>
<td><input type="text" name="u7" /></td>
</tr>
<tr>
<td>Reciever Phonenumber</td>
<td><input type="text" name="u8" /></td>
</tr>
</table>
<input type="submit" id="details" value="Send Post">
</form>
</center>
</body> 
</html>