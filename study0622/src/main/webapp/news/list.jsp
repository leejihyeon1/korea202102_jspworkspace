<%@page import="com.korea.study0622.model.domain.News"%>
<%@page import="java.util.List"%>
<%@page import="com.korea.stuedy0622.model.news.dao.MybatisNewsDAO"%>
<%@page import="com.korea.stuedy0622.model.news.dao.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!NewsDAO newsDAO = new MybatisNewsDAO();%>
<%
List<News> list = newsDAO.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<h2>뉴스 목록</h2>

	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%
		for (News news : list) {
		%>
		<tr>
			<td><%=news.getNews_id()%></td>
			<td><a href="/news/detail.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle()%></a></td>
			<td><%=news.getWriter()%></td>
			<td><%=news.getRegdate()%></td>
			<td><%=news.getHit()%></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="5">
				<button onClick="location.href='/news/regist.jsp'">등록</button>
			</td>
	</table>

</body>
</html>