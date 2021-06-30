package com.korea.study0622.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.model.domain.Member;
import com.korea.stuedy0622.model.member.dao.MemberDAO;
import com.korea.stuedy0622.model.member.dao.MybatisMemberDAO;

public class LoginServlet extends HttpServlet{
	MemberDAO memberDAO;
	@Override
	public void init() throws ServletException {
		memberDAO=new MybatisMemberDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("email");
		String name=request.getParameter("name");
		
		Member member=memberDAO.selectByKakao(user_id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(member==null) {
			out.print("존재하지 않는 계정입니다");
		}else {
			out.print("환영합니다 "+name+"님");
		}
	}
}
