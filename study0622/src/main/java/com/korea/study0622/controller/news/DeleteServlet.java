package com.korea.study0622.controller.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.news.dao.MybatisNewsDAO;
import com.korea.stuedy0622.model.news.dao.NewsDAO;

public class DeleteServlet extends HttpServlet{
	NewsDAO newsDAO;
	MessageObject msg;
	
	@Override
	public void init() throws ServletException {
		newsDAO=new MybatisNewsDAO();
		msg=new MessageObject();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int news_id=Integer.parseInt(request.getParameter("news_id"));
		int result=newsDAO.delete(news_id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(result<1) {
			out.print(msg.getMsgBack("삭제 실패"));
		}else {
			out.print(msg.getMsgURL("삭제 성공","/news/list.jsp"));
		}
	}
}
