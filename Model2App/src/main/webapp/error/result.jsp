<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
<img src="/images/error.png">
<p>
<%
RuntimeException e=(RuntimeException)request.getAttribute("obj"); 
out.print(e);
%>
</body>
</html>