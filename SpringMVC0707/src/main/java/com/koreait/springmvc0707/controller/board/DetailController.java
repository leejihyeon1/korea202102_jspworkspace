package com.koreait.springmvc0707.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc0707.model.board.service.BoardService;
import com.koreait.springmvc0707.model.domain.Board;

import lombok.Setter;

//상세보기 요청을 처리하는 하위 컨트롤러
@Setter
public class DetailController implements Controller{
	private BoardService boardService;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계
		String board_id=request.getParameter("board_id");
		int pk=Integer.parseInt(board_id);
		Board board=boardService.select(pk);
		
		//4단계
		ModelAndView mav=new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("board/detail");
		return mav;
	}
	

}
