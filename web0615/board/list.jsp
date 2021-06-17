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

		String sql="select * from board order by board_id desc";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
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
	<table width="100%" border="1px">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%while(rs.next()){%>
		<tr>
			<td>No</td>
			<td><%=rs.getString("title")%></td>
			<td><%=rs.getString("writer")%></td>
			<td><%=rs.getString("regdate")%></td>
			<td><%=rs.getInt("hit")%></td>
		</tr>
		<%}%>
</body>
</html>
