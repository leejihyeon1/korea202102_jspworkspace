package com.koreait.mvcframework.controller.blood;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvcframework.controller.Controller;
import com.koreait.mvcframework.model.blood.BloodService;

//혈액형 요청을 처리하는 컨트롤러 클래스
public class BloodController implements Controller{
	BloodService service;
	public BloodController() {
		service=new BloodService();
	}
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String blood=request.getParameter("blood");
		
		//이미 기존에 작성해 두었던 로직을 재사용한다!(모델)
		String msg=service.getAdvice(blood);//3단계 일시킨다!
		//4단계 :결과 저장
		request.setAttribute("msg", msg);//request객체는 사실 map임
		
		//아래와 같이 요청을 끊고, 클라이언트가 재접속하게 하지 말고,
		//서버에서 특정 자원으로 요청을 포워딩 즉 전달시켜보자!
		//response.sendRedirect("/blood/result.jsp?msg="+msg);//지정한 url을 재요청
		

	}
	@Override
	public String getViewName() {
		return "/blood/result.jsp";
	}
	
	
	
}
