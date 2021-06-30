package com.korea.study0622.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.korea.study0622.model.domain.Member;
import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.member.dao.MemberDAO;
import com.korea.stuedy0622.model.member.dao.MybatisMemberDAO;

public class LoginServlet extends HttpServlet{
	MemberDAO memberDAO;
	MessageObject msg;
	@Override
	public void init() throws ServletException {
		memberDAO=new MybatisMemberDAO();
		msg=new MessageObject();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String password=request.getParameter("password");	
		
		Member member=new Member();
		member.setUser_id(user_id);
		member.setPassword(password);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Member obj= null;
		obj=memberDAO.select(member);
		if(obj==null) {
			out.print(msg.getMsgBack("로그인 실패"));
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("member", obj);
			out.print(msg.getMsgURL("로그인 성공", "/admin/main.jsp"));
		}
	}

}
