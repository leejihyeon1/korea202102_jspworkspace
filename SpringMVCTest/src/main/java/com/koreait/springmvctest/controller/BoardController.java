package com.koreait.springmvctest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.springmvctest.model.board.dao.BoardDAO;

//게시판과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class BoardController {
	private BoardDAO boardDAO;
	
	@RequestMapping("/board/list")
	public String selectAll(Model model) {
		List list=boardDAO.selectAll();
		model.addAttribute("boardList",list);
		return "board/list";
	}
}
