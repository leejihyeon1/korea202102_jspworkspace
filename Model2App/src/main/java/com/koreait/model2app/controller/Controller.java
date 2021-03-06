package com.koreait.model2app.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 하위컨트롤러가 반드시 구현해야 할 메서드 정의
public interface Controller {
	//형님으로부터 받은 요청 정보를 이용ㅎ여 실제 요청을 처리하는 메서드
	public void execute(HttpServletRequest request, HttpServletResponse response);
	public String getViewName();//응답시 보여질 결과페이지
	public boolean isForward();//포워딩 필요여부 판단
}
