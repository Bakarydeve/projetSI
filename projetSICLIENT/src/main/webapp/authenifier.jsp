<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="donnees.Compte" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css.css" type="text/css">
<title>Compte utilisateur</title>
</head>
<body>
	<% 
		Compte cpt = (Compte) session.getAttribute("utilisateur");
	%>
<h1> Info</h1>
<ul>
	<li> Login : <%=cpt.getPseudo()%> 
</ul>
</body>
</html>