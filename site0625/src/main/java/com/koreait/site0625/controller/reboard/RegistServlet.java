package com.koreait.site0625.controller.reboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0625.model.domain.ReBoard;
import com.koreait.site0625.model.reboard.dao.JdbcReBoardDAO;
import com.koreait.site0625.model.reboard.dao.MybatisReBoardDAO;
import com.koreait.site0625.model.reboard.dao.ReBoardDAO;
import com.koreait.site0625.util.message.MessageObject;

public class RegistServlet extends HttpServlet{
	ReBoardDAO reboardDAO;
	MessageObject msg;
	@Override
	public void init() throws ServletException {
		reboardDAO=new MybatisReBoardDAO();
		//reboardDAO=new JdbcReBoardDAO();
		msg=new MessageObject();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		int reboard_id=Integer.parseInt(request.getParameter("reboard_id"));
		
		ReBoard reboard=new ReBoard();
		reboard.setTitle(title);
		reboard.setWriter(writer);
		reboard.setContent(content);
		reboard.setReboard_id(reboard_id);
		
		int result=reboardDAO.insert(reboard);
		
		//쿼리 수행 후 vo에는 최근에 증가된 pk가 들어있을것이다!
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//out.print("글 등록 후 반환된 pk는 "+reboard.getReboard_id());
		if(result<1) {
			out.print(msg.getMsgBack("글쓰기 실패"));
		}else {
			out.print(msg.getMsgURL("글쓰기 성공", "/reboard/list.jsp"));
		}
		
	}

}
