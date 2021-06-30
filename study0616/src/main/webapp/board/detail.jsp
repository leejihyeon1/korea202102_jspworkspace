<%@page import="study0616.model.domain.Board"%>
<%@page import="study0616.board.model.dao.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	BoardDAO boardDAO=new BoardDAO();
%>
<%
	request.setCharacterEncoding("utf-8");
	int board_id=Integer.parseInt(request.getParameter("board_id"));
	Board board=boardDAO.select(board_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace( "content" );
	
	$($("input[type='button']")[0]).click(function(){
		alert("수정");
		edit();
	});
	$("#bt2").click(function(){
		alert("삭제");
		del();
	});
	$("#bt3").click(function(){
		alert("목록");
		location.href="/board/list.jsp";
	});
});
function edit(){
	$("form").attr({
		"action":"/board/update.jsp",
		"method":"POST"
	});
	$("form").submit();
}
function del(){
	$("form").attr({
		"action":"/board/delete.jsp",
		"method":"POST"
	});
	$("form").submit();
}
</script>
<body>

<h3>등록 </h3>

<div class="container">
  <form >
	<input type="hidden" name="board_id" value="<%=board.getBoard_id()%>">
    <input type="text" id="fname" name="title" value="<%=board.getTitle()%>">

    <input type="text" id="lname" name="writer" value="<%=board.getWriter()%>">

    <textarea id="subject" name="content" placeholder="내용" style="height:200px"><%=board.getContent()%></textarea>

    <input type="button" value="수정" id="bt1">
    <input type="button" value="삭제" id="bt2">
    <input type="button" value="목록" id="bt3">
  </form>
</div>

</body>
</html>
