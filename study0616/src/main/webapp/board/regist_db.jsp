<%@page import="study0616.model.domain.Board"%>
<%@page import="study0616.board.model.dao.BoardDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	BoardDAO boardDAO=new BoardDAO();
%>
<%
	request.setCharacterEncoding("utf-8");
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	
	Board board=new Board();
	board.setTitle(title);
	board.setWriter(writer);
	board.setContent(content);
	
	int result=boardDAO.insert(board);
	
	
	out.print("<script>");
	if(result<1){
		out.print("alert('글 등록 실패');");
		out.print("history.back();");
	}else{
		out.print("alert('글 등록 성공');");
		out.print("location.href='/board/list.jsp'");
	}
	out.print("</script>");
	

%>