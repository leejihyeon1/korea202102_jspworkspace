package com.koreait.site0625.controller.reboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0625.model.domain.ReBoard;
import com.koreait.site0625.model.reboard.dao.MybatisReBoardDAO;
import com.koreait.site0625.model.reboard.dao.ReBoardDAO;
import com.koreait.site0625.util.message.MessageObject;

public class ReplyServlet extends HttpServlet{
	ReBoardDAO reboardDAO;
	MessageObject msg;
	@Override
	public void init() throws ServletException {
		reboardDAO=new MybatisReBoardDAO();
		msg=new MessageObject();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int team=Integer.parseInt(request.getParameter("team"));
		int step=Integer.parseInt(request.getParameter("step"));
		int depth=Integer.parseInt(request.getParameter("depth"));
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//vo담기
		ReBoard reboard=new ReBoard();
		reboard.setTeam(team);
		reboard.setStep(step);
		reboard.setDepth(depth);
		reboard.setTitle(title);
		reboard.setWriter(writer);
		reboard.setContent(content);
		
		//답변 등록을 위한 메서드 호출
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		int result=reboardDAO.reply(reboard);
		if(result<1) {
			out.print(msg.getMsgBack("답변 등록 실패"));
		}else {
			out.print(msg.getMsgURL("답변 등록 성공", "/reboard/list.jsp"));
		}
	}

}
