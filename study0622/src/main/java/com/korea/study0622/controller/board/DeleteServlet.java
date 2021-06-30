package com.korea.study0622.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.board.dao.BoardDAO;
import com.korea.stuedy0622.model.board.dao.MybatisBoardDAO;

public class DeleteServlet extends HttpServlet{
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
		
		String board_id=request.getParameter("board_id");
		int result=boardDAO.delete(Integer.parseInt(board_id));
		if(result<1) {
			out.print(messageObject.getMsgBack("삭제 실패"));
		}else {
			out.print(messageObject.getMsgURL("삭제 성공","/board/list.jsp"));
		}
	}

}
