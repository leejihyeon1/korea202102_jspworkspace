package com.koreait.springmvc0714.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc0714.model.board.service.BoardService;

import lombok.Setter;

//삭제 요청 처리하는 하위 컨트롤러
@Setter
public class DeleteController implements Controller{
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		
		try {
			//3단계 일시키기
			boardService.delete(Integer.parseInt(request.getParameter("board_id")));
			mav.setViewName("redirect:/board/list");//목록으로 재접속
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mav.addObject("e",e);
		}
		//4단계 생략, 목록으로 재접속
		return mav;
	}

}
