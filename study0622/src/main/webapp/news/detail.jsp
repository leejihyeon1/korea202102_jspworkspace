<%@page import="com.korea.study0622.model.domain.News"%>
<%@page import="com.korea.stuedy0622.model.news.dao.MybatisNewsDAO"%>
<%@page import="com.korea.stuedy0622.model.news.dao.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	NewsDAO newsDAO=new MybatisNewsDAO();
%>
<%
	request.setCharacterEncoding("utf-8");
	int news_id=Integer.parseInt(request.getParameter("news_id"));
	News news=newsDAO.select(news_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$($("input[type='button']")[0]).click(function(){
			edit();
		});
		$($("input[type='button']")[1]).click(function(){
			del();
		});
		$($("input[type='button']")[2]).click(function(){
			location.href="/news/list.jsp";
		});
	})
	
	function edit(){
		$("form").attr({
			"action":"/news/update",
			"method":"post"
		});
		$("form").submit();
	}
	
	function del(){
		$("form").attr({
			"action":"/news/delete",
			"method":"post"
		});
		$("form").submit();
	}
</script>
</head>
<body>

<h3>뉴스 등록</h3>

<div class="container">
  <form >
	<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
    <input type="text"  name="title" value="<%=news.getTitle()%>">
    <input type="text" " name="writer" value="<%=news.getWriter()%>">
    <textarea id="subject" name="content"  style="height:200px"><%=news.getContent()%></textarea>

    <input type="button" value="수정">
    <input type="button" value="삭제">
    <input type="button" value="목록">
  </form>
</div>

</body>
</html>