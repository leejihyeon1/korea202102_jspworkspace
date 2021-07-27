package com.koreait.springdb.model.repository.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.springdb.model.domain.Board;

@Repository //컴포넌트 스캔의 대상이 되어 자동 인스턴스 생성됨
public class MybatisBoardDAO implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlsessiontemplate;
	
	@Override
	public List selectAll() {
		return sqlsessiontemplate.selectList("Board.selectAll");
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Board select(int board_id) {
		return sqlsessiontemplate.selectOne("Board.select", board_id);
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
