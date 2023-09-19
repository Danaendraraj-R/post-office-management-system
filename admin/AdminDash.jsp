<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
<meta http-equiv="refresh" />
<script>
  function preventBack(){window.history.forward();}
  setTimeout("preventBack()", 0);
  window.onunload=function(){null};
</script>
    </head>
    <body>
<% if(request.getRemoteUser()==null)
	{
	response.sendRedirect("AdminLogin.jsp");	
	}
	%>
<center>
        <h1>Admin Dashboard</h1>
    <a href="ADDPOS.jsp">Add Post Office</a>
	<a href="ViewPO">View List of Post Office</a>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
  </form>
</center>
</body> 
</html>