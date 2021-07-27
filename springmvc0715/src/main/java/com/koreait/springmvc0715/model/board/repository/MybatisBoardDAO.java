package com.koreait.springmvc0715.model.board.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.springmvc0715.exception.DMLException;
import com.koreait.springmvc0715.model.board.repository.BoardDAO;
import com.koreait.springmvc0715.model.domain.Board;

import lombok.Setter;

@Setter
@Repository
public class MybatisBoardDAO implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectAll() {
		List boardList =sessionTemplate.selectList("Board.selectAll");
		return boardList;
	}

	@Override
	public void insert(Board board) throws DMLException{ //여기서 에러를 처리해버리면,
		sessionTemplate.insert("Board.insert", board); //자동커밋 
	}

	@Override
	public Board select(int board_id) {
		return sessionTemplate.selectOne("Board.select", board_id);
	}

	@Override
	public void update(Board board) throws DMLException{
		sessionTemplate.update("Board.update", board);
	}

	@Override
	public void delete(int board_id) throws DMLException{
		sessionTemplate.delete("Board.delete", board_id);
	}
}
