<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
out.println("Hello :- "+session.getAttribute("name")+",");
%></br>
	<%
out.println("Thanks for your interest. Our agent will contactt you on: "+session.getAttribute("email"));
%>
</body>
</html>