package com.koreait.model2app.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.board.dao.BoardDAO;
import com.koreait.model2app.model.board.dao.JdbcBoardDAO;
import com.koreait.model2app.model.board.dao.MybatisBoardDAO;

//board의 요청 중 목록 요청을 처리하는 하위 컨트롤러
public class ListController implements Controller{
	BoardDAO boardDAO;
	
	public ListController() {
		//boardDAO=new MybatisBoardDAO();
		boardDAO=new JdbcBoardDAO();
	}
	
	//3) 일 시키기 4) 결과 저장  ==> 하위 컨트롤러가 하는 일(두가지)
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List list=boardDAO.selectAll();//3) 일 시키기
		request.setAttribute("boardList", list);//4) 결과저장
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/result/board/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
