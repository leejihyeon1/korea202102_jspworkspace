package com.koreait.model2app.controller.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.movie.MovieService;

//영화에 대한 요청을 처리하는 하위 컨트롤러
//3) 알맞는 객체에 일 시킨다 4) 저장할 것이 있다면 결과저장
public class MovieController implements Controller{
	MovieService service;
	
	public MovieController() {
		service=new MovieService();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String movie=request.getParameter("movie");
		String msg=service.getAdvice(movie);//3)
		
		request.setAttribute("msg", msg);//4)
		
	}

	@Override
	public String getViewName() {
		return "/movie/result";
	}

	
}
