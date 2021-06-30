<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int totalRecord=465;//총 게시물 수
	int pageSize=10;//한 페이지에 보일 게시물 수
	int blockSize=10;//한 화면에 보일 버튼의 개수
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);//총 페이지 수
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int firstPage=currentPage-((currentPage-1)%blockSize);
	int lastPage=firstPage+ (blockSize-1);
	int num=totalRecord-((currentPage-1)*pageSize);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
  position:relative;

}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
.pageNum{
	color:red;
	font-size: 20px;
	font-weight: bold;	
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

</script>
</head>
<body>

<h2>게시판 목록</h2>

<table>
	<tr>
		<th>No</th>
		<th>이미지</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	<%for(int i=1; i<=pageSize; i++){ %>
	<%if(num<1)break; %>
	<tr>
		<td><%=num--%></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>

	</tr>
	<%} %>
	<tr>
		<td colspan='6' style='text-align: center'>
		<%if(firstPage>1){ %>
		<a href="/test/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
		<%} %>
		<%for(int i=firstPage; i<=lastPage; i++) {%>
		<%if(i>totalPage)break; %>
		<a href="/test/list.jsp?currentPage=<%=i%>" <%if(currentPage==i){ %> class="pageNum" <%} %>>[<%=i %>]</a>
		<%} %>
		<%if(lastPage<totalPage){%>
		<a href="/test/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
		<%} %>
		</td>
	</tr>
</table>
</body>
</html>
