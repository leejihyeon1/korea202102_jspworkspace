<%@page import="study0616.model.domain.Board"%>
<%@page import="study0616.board.model.dao.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	BoardDAO boardDAO=new BoardDAO();
%>
<%
	request.setCharacterEncoding("utf-8");
	int board_id=Integer.parseInt(request.getParameter("board_id"));
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	
	Board board=new Board();
	board.setBoard_id(board_id);
	board.setTitle(title);
	board.setWriter(writer);
	board.setContent(content);
	
	int result=boardDAO.update(board);
	out.print("<script>");
	if(result<1){
		out.print("alert('수정실패');");
		out.print("history.back();");
	}else{
		out.print("alert('수정성공');");
		out.print("location.href='/board/detail.jsp?board_id='"+board_id+";");
	}
	out.print("</script>");
%>