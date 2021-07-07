package com.koreait.model2app.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.board.dao.BoardDAO;
import com.koreait.model2app.model.board.dao.JdbcBoardDAO;
import com.koreait.model2app.model.board.dao.MybatisBoardDAO;
import com.koreait.model2app.model.domain.Board;

//게시판의 수정 요청을 처리하는 하위 컨트롤러 정의 
public class EditController implements Controller{
	//3단계: 로직 객체에 일 시킨다 , 4단계: 결과가 있다면 결과저장 
	BoardDAO boardDAO; 
	
	public EditController() {
//		boardDAO = new MybatisBoardDAO();
		boardDAO=new JdbcBoardDAO();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//파라미터 받기
		String board_id=request.getParameter("board_id");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//VO에 심기 
		Board board = new Board();
		board.setBoard_id(Integer.parseInt(board_id));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		boardDAO.update(board); //3단계: 일 시키기!!!
		
		//request.setAttribute("board", board); //4단계: vo 심기
	}

	public String getViewName() {
		return "/result/board/edit";
	}

	public boolean isForward() {
		return true;
	}
	
	
}