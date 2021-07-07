package com.koreait.springmvctest.model.board.dao;

import java.util.List;

import com.koreait.springmvctest.model.domain.Board;

//모델 파트의 모든 하위 DAO들이 반드시 정의할 메서드 정의
public interface BoardDAO {
	public List selectAll();
	public int insert(Board board);
	public Board select(int board_id);
	public int update(Board board);
	public int delete(int board_id);
}
