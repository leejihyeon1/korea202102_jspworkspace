package com.koreait.site0622.controller.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.comments.dao.CommentsDAO;
import com.koreait.site0622.model.comments.dao.JdbcCommentsDAO;
import com.koreait.site0622.model.comments.dao.MybatisCommentsDAO;
import com.koreait.site0622.model.domain.Comments;

//댓글 등록 요청 처리 서블릿(클라이언트 요청이 비동기 방식이므로, 디자인을 결과로 보내서는 안됨!)
//왜? 전체 새로고침이 일어나지 않는 방식이라 디자인을 보낼 의미가 없음 즉 전체 페이지 변환이 목적이 아니라
//페이지의 일부의 변경이 목적임
public class RegistServlet extends HttpServlet{
	CommentsDAO commentsDAO;
	@Override
	public void init() throws ServletException {
		commentsDAO=new MybatisCommentsDAO();
		//commentsDAO=new JdbcCommentsDAO();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg=request.getParameter("msg");
		String cwriter=request.getParameter("cwriter");
		int news_id=Integer.parseInt(request.getParameter("news_id"));
		
		Comments comments=new Comments();
		comments.setMsg(msg);
		comments.setCwriter(cwriter);
		comments.setNews_id(news_id);
		
		int result=commentsDAO.insert(comments);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(result);
	}
}
