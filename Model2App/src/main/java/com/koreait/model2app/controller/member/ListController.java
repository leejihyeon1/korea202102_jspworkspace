package com.koreait.model2app.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.member.service.MemberService;
import com.koreait.model2app.model.member.service.MemberServiceImpl;

public class ListController implements Controller{
	MemberService memberService;
	public ListController() {
		memberService=new MemberServiceImpl();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List memberList=memberService.selectAll();//3)알맞는 로직 객체에 일 시키기
		request.setAttribute("memberList", memberList);//4)요청 객체에 결과 담기
	}
	public String getViewName() {
		return "/result/member/list";
	}
	public boolean isForward() {
		return true;
	}
}