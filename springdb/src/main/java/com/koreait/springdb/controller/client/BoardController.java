package com.koreait.springdb.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.springdb.model.domain.Board;
import com.koreait.springdb.model.service.board.BoardService;

import lombok.Setter;

@Controller //스프링의 컴포넌트 스캔의 대상이 되기 위해..
@Setter
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public void test() {
		System.out.println("클라이언트의 test()호출");
	}
	
	//게시판 목록 요청을 처리 
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String getList(Model model) {
		//3단계 일시키기
		List boardList=boardService.selectAll();
		
		//4단계 결과 저장
		model.addAttribute("boardList",boardList);//model에 추가한것은 application,session,request 중 request에 저장됨
		//그래서 포워딩 하는 것,,
		return "user/board/list";
	}
	
	//상세보기
	@RequestMapping(value="/board/detail", method = RequestMethod.GET)
	public String getDetail(int board_id,Model model) {
		//3단계
		Board board=boardService.select(board_id);
		
		//4단계
		model.addAttribute(board);
		
		return "user/board/detail";
	}
}
