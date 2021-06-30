package com.korea.study0622.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.model.domain.Member;
import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.member.dao.MemberDAO;
import com.korea.stuedy0622.model.member.dao.MybatisMemberDAO;

public class RegistServlet extends HttpServlet{
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
		String name=request.getParameter("name");
		
		Member member=new Member();
		member.setUser_id(user_id);
		member.setPassword(password);
		member.setName(name);
		
		int result=memberDAO.regist(member);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		if(result<1) {
			out.print(msg.getMsgBack("회원가입 실패"));
		}else {
			out.print(msg.getMsgURL("회원가입 성공", "/member/login.jsp"));
		}
	}
}
