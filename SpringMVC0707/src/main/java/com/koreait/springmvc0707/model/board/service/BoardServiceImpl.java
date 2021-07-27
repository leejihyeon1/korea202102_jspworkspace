package com.koreait.springmvc0707.model.board.service;

import java.util.List;

import com.koreait.springmvc0707.model.board.repository.BoardDAO;
import com.koreait.springmvc0707.model.domain.Board;

import lombok.Setter;

//서비스를 구현한 객체
@Setter
public class BoardServiceImpl implements BoardService{
	private BoardDAO boardDAO;//스프링의 컨테이너로부터 주입받자(DI)
	
	@Override
	public List selectAll() {
		System.out.println("서비스의 selectAll()호출");		
		return boardDAO.selectAll();
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int board_id) {
		// TODO Auto-generated method stub
		
	}

}
