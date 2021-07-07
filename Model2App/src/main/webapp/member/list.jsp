<%@page import="com.koreait.springmvctest.model.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Member> memberList=(List)request.getAttribute("memberList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%" border="1px">
		<tr>
			<th>이미지</th>
			<th>이름</th>
			<th>연락처</th>
			<th>주소</th>
		</tr>
		<%for(Member member:memberList){ %>
		<tr>
			<td>
				<img alt="" src="/data/<%=member.getPhoto()%>" width="45px">
			</td>
			<td>
				<a href="/member/detail.do?member_id=<%=member.getMember_id()%>"><%=member.getName() %></a>
			</td>
			<td><%=member.getName() %></td>
			<td><%=member.getPhone() %></td>
			<td><%=member.getAddr() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="3"><button onClick="location.href='/member/regist.jsp';">회원등록</button>
		</tr>
	</table>
</body>
</html>