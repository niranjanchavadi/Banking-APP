<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
The statement details are as follows:</br>
<%
out.println("The amount spent is: "+session.getAttribute("al1"));
%></br>
<%
out.println("The Accno are: "+session.getAttribute("al2"));
%></br>
</body>
</html>