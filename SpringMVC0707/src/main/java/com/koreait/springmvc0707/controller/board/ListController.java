package com.koreait.springmvc0707.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc0707.model.board.service.BoardService;

import lombok.Data;

//게시판 목록 요청을 처리하는 하위 컨트롤러
@Data
public class ListController implements Controller{
	private BoardService boardService;
	//3,4단계 수행
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계 : 일시키기
		List boardList=boardService.selectAll();
		System.out.println("결과는 "+boardList);
		
		//4단계 : 결과저장
		request.setAttribute("boardList", boardList);
		
		ModelAndView mav=new ModelAndView();//model(보관할 데이터 넣을 수 있는 객체) + view(뷰 이름을 갖는 객체)
		mav.addObject("board/list");//넘겨받아 jsp로 해석됨
		
		return mav;
	}

}
