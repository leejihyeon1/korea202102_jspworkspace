<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//이 jsp를 접근하는 클라이언트에 쿠키가 존재하지 않는 다면 이 클라이언트의 이 jsp를
	//처음 접근한다고 보아야 하므로, 톰캣이 이 클라이언트에게 세션ID생성하여 발급하게 되는데,
	//id를 출력해본다
	String sessionId=session.getId();//고양이가 클라이언트에게 발급한 세션 고유 번호
	//(즉 고양이가 최초의 접근이라고 판단할 경우 발급)
	out.print("서버가 나에게 발급한 세션 아이디는 "+sessionId);
	session.setAttribute("name", "tiger");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>