package com.koreait.site0622.controller.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.domain.News;
import com.koreait.site0622.model.news.dao.MybatisNewsDAO;
import com.koreait.site0622.model.news.dao.NewsDAO;
import com.koreait.site0622.util.message.MessageObject;

public class RegistServlet extends HttpServlet{
	NewsDAO newsDAO;
	MessageObject messageObject;
	@Override
	public void init() throws ServletException {
		newsDAO=new MybatisNewsDAO();
		messageObject=new MessageObject();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//vo담기
		News news=new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		//dao 일시키기
		int result=newsDAO.insert(news);//다형성! newsDAO인줄 알았지만 막상 동작할땐 mybatisnewsdao가 동작함
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if(result==0) {
			out.print(messageObject.getMsgBack("등록실패"));
		}else {
			out.print(messageObject.getMsgURL("등록완료","/news/list.jsp"));
		}
	}
}
