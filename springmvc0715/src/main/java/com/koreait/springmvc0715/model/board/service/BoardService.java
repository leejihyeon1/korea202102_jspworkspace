package com.koreait.springmvc0715.model.board.service;

import java.util.List;

import com.koreait.springmvc0715.model.domain.Board;

public interface BoardService {
	public List selectAll();
	public void insert(Board board);
	public Board select(int board_id);
	public void update(Board board);
	public void delete(int board_id);

}
