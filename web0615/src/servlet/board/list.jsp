<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%!
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
%>
<%
	//드라이버 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//접속
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	if(con!=null){
		out.print("접속 성공 <br>");
	}else{
		out.print("접속 실패 <br>");
	}
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
</head>
<body>

</body>
</html>
