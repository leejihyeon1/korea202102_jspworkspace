package com.koreait.model2app.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.board.dao.BoardDAO;
import com.koreait.model2app.model.board.dao.JdbcBoardDAO;
import com.koreait.model2app.model.board.dao.MybatisBoardDAO;
import com.koreait.model2app.model.domain.Board;

//게시판 글쓰기 요청을 처리하는 하위 컨트롤러
public class RegistController implements Controller{
	BoardDAO boardDAO;
	
	public RegistController() {
		//boardDAO=new MybatisBoardDAO();
		boardDAO=new JdbcBoardDAO();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 넘겨받아 vo 채우기
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board=new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		boardDAO.insert(board);
		
	}

	@Override
	public String getViewName() {
		return "/result/board/regist";
	}

	@Override
	public boolean isForward() {
		return false;
	}
	
	

}
