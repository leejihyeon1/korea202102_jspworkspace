package com.koreait.springmvc0707.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc0707.model.board.service.BoardService;
import com.koreait.springmvc0707.model.domain.Board;

import lombok.Setter;

//수정요청을 처리하는 하위 컨트롤러
@Setter
public class UpdateController implements Controller{
	private BoardService boardService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계
		String board_id=request.getParameter("board_id");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board=new Board();
		board.setBoard_id(Integer.parseInt(board_id));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		
		//4단계 : 결과를 저장하지 않고 board/detail?board_id=7로 redirect할것 즉, 다시 접속을 유도
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/board/detail?board_id="+board_id);//redirect: 를 적용하면 forwarding이 아닌 redirect
		return mav;
	}

}
