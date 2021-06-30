package com.koreait.site0625.model.reboard.dao;

import java.util.List;

import com.koreait.site0625.model.domain.ReBoard;

public interface ReBoardDAO {
	public int insert(ReBoard reboard);
	public List selectAll();
	public ReBoard select(int board_id);
	public int update(ReBoard reboard);
	public int delete(int board_id);
	//public int updateStep(ReBoard reboard);//답변 자리 확보
	public int reply(ReBoard reboard);//답변 등록
}
