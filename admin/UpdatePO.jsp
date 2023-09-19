<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
</style>
<title>Update Post Office</title>
<meta http-equiv="refresh" />
</head>
<body>
<center><h1>Update Post Office</h1></center>
<form action="UpdatePO" method="post">
<table style="with: 50%" align="center">
<tr>
<tr>
<td>PO ID</td>
<% if(request.getRemoteUser()==null)
	{
	response.sendRedirect("AdminLogin.jsp");	
	}
	%>
<input type="hidden" value="<%=request.getParameter("poid")%>" name="poid"/>
<td>
<%=request.getParameter("poid")%>
</td>
</tr>
<tr>
<td>Enter an Option</td>
<td>
<select name = "options">  
<option value="1">uniqueid</option>  
<option value="2">poname</option>  
<option value="3">address</option>  
<option value="4">pincode</option>  
<option value="5">city</option>
<option value="6">state</option>  
<option value="7">telephone</option>
</select>
</td> 
</tr>
<tr>
<td>Enter New data:</td>
<td><input type="text" name="Newdata" /></td>
</tr>
</table>
<center><h2><input type="submit" value="Update" /><h2></center>
</form>
</body>
</html>
