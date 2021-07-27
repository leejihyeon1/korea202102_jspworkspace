package com.koreait.springmvc0715.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.springmvc0715.model.board.service.BoardService;
import com.koreait.springmvc0715.model.domain.Board;

import lombok.Setter;

//목록, 등록, 수정, 삭제, 상세보기를 하나의 컨트롤러에서 처리~
//아래와 같이 @가 붙은 대상 객체를 가리켜 컴포넌트라 한다
@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//목록 요청을 처리하는 메서드
	@RequestMapping(value="/board/list",method=RequestMethod.GET)
	public String getList(Model model) {
		//3단계 일시키기
		List boardList=boardService.selectAll();
		
		//4단계 결과저장
		model.addAttribute("boardList",boardList);
		return "board/list";
	}
	
	//글쓰기 요청 처리
	@RequestMapping(value="/board/regist",method=RequestMethod.POST)
	public String regist(Board board) {//Board VO에 들어있는 멤버변수와 html의 파라미터명이 동일하다면 자동 매핑..
		//3단계 일시키기
		System.out.println(board.getTitle());
		
		String viewName=null;
		try {
			boardService.insert(board);
			viewName="redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		//개발자가 String형으로 뷰 이름을 반환하면 내부적으로는 , modelandview의 setviewname() 작성하는 것과 동일
		return viewName;
	}
	
	//상세보기 요청처리
	@RequestMapping(value="/board/detail",method = RequestMethod.GET)
	public String getDetail(int board_id,Model model) {
		//3단계 일시키기
		Board board=boardService.select(board_id);
		
		//4단계 결과저장
		model.addAttribute(board);
		return "board/detail";
	}
	
	//수정 요청 처리
	@RequestMapping(value="/board/update", method = RequestMethod.POST)
	public ModelAndView edit(Board board) {
		//3단계 수정
		ModelAndView mav=new ModelAndView();
		try {
			boardService.update(board);
			mav.setViewName("redirect:/board/detail?board_id="+board.getBoard_id());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	//삭제 요청 처리
	@RequestMapping(value="/board/del",method = RequestMethod.POST)
	public String delete(int board_id,Model model) {
		//3단계 일시키기
		String viewName=null;
		try {
			boardService.delete(board_id);
			viewName="redirect:/board/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewName;
	}
}
