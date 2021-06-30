package com.korea.study0622.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.model.domain.Board;
import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.board.dao.BoardDAO;
import com.korea.stuedy0622.model.board.dao.MybatisBoardDAO;

public class RegistServlet extends HttpServlet{
	BoardDAO boardDAO;
	MessageObject messageObject;
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardDAO=new MybatisBoardDAO();
		messageObject=new MessageObject();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		Board board=new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result=boardDAO.insert(board);
		if(result<1) {
			out.print(messageObject.getMsgBack("등록 실패"));
		}else {
			out.print(messageObject.getMsgURL("등록 성공","/board/list.jsp"));
		}
	}

}
