package com.koreait.springmvc0714.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc0714.model.board.service.BoardService;
import com.koreait.springmvc0714.model.domain.Board;

import lombok.Setter;

//게시물 등록 요청을 처리할 하위 컨트롤러 
@Setter
public class RegistController implements Controller{
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계 일시키기
		Board board=new Board();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		ModelAndView mav=new ModelAndView("/board/regist");
		try {
			boardService.insert(board);//등록 일 시킴!!
			//성공의 메세지, 페이지를 보여주기
			System.out.println("등록시 성공");
			mav.setViewName("redirect:/board/list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("등록시 에러 발생");
		}
		
		//4단계 - 저장할게 없으므로 생략(이때 포워딩 하지말고, redirect 해야함 즉 지정한 url로 재접속 시켜야 함!)
		return mav;
	}
 
}
