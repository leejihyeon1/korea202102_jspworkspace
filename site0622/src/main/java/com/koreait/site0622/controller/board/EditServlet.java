package com.koreait.site0622.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.board.dao.MybatisBoardDAO;
import com.koreait.site0622.model.domain.Board;
import com.koreait.site0622.util.message.MessageObject;

//수정 요청을 처리하는 서블릿
public class EditServlet extends HttpServlet{
	MybatisBoardDAO boardDAO;
	MessageObject messageObject;
	
	@Override
	public void init() throws ServletException {
		boardDAO=new MybatisBoardDAO();
		messageObject=new MessageObject();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 받아서 db에 update
		request.setCharacterEncoding("utf-8");
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String board_id=request.getParameter("board_id");
		
		//넘겨받은 파라미터를 vo에 담자
		Board board=new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setBoard_id(Integer.parseInt(board_id));
		
		//DAO에 일 시키자
		int result=boardDAO.update(board);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(result>0) {
			out.print(messageObject.getMsgURL("수정 성공", "/board/detail.jsp?board_id="+board_id));
		}else {
			out.print(messageObject.getMsgBack("수정 실패"));

		}
	}
}