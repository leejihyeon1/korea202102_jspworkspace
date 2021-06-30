package com.korea.stuedy0622.model.board.dao;



import java.util.List;

import com.korea.study0622.model.domain.Board;

public interface BoardDAO {
	public int insert(Board board);
	public List selectAll();
	public Board select(int board_id);
	public int update(Board board);
	public int delete(int board_id);
}
