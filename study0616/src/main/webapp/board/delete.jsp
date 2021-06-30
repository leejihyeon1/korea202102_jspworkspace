<%@page import="study0616.board.model.dao.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	BoardDAO boardDAO=new BoardDAO();
%>
<%
	String board_id=request.getParameter("board_id");
 	int result=boardDAO.delete(Integer.parseInt(board_id));
 	
	out.print("<script>");
	if(result<1){
		out.print("alert('삭제실패');");
		out.print("history.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='/board/list.jsp;");
	}
	out.print("</script>");
%>