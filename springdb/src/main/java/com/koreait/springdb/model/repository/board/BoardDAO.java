package com.koreait.springdb.model.repository.board;

import java.util.List;

import com.koreait.springdb.model.domain.Board;

public interface BoardDAO {
	public List selectAll();
	public void insert(Board board);
	public Board select(int board_id);
	public void update(Board board);
	public void delete(int board_id);
}
