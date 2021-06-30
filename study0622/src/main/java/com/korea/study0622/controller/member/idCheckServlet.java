package com.korea.study0622.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.study0622.model.domain.Member;
import com.korea.study0622.util.message.MessageObject;
import com.korea.stuedy0622.model.member.dao.MemberDAO;
import com.korea.stuedy0622.model.member.dao.MybatisMemberDAO;

public class idCheckServlet extends HttpServlet{
	MemberDAO memberDAO;

	@Override
	public void init() throws ServletException {
		memberDAO=new MybatisMemberDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//프린트 객체 생성
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//파라미터 값 받기
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		
//		Member member=new Member();
//		member.setUser_id(user_id);
//		member.setPassword(password);
//		member.setName(name);
		
		Member member=memberDAO.getMemberId(user_id);
//		out.print("<script>");
//		if(member==null) {
//			out.print("alert('회원가입 가능');");
//			out.print("location.href='/member/signup.jsp';");
//		}else {
//			out.print("alert('회원가입 실패');");
//			out.print("location.href='/member/signup.jsp';");
//		}
//		out.print("</script>");
		if(member==null) {
			out.print("true");
		}else {
			out.print("false");
		}
		
	}
}
