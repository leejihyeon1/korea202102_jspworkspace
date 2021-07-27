package com.koreait.springmvc0707.model.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.springmvc0707.model.board.repository.BoardDAO;
import com.koreait.springmvc0707.model.domain.Board;
import com.koreait.springmvc0707.model.mybatis.MybatisConfigManager;

import lombok.Setter;

@Setter
public class MybatisBoardDAO implements BoardDAO{
	private MybatisConfigManager configManager;
	
	@Override
	public List selectAll() {
		SqlSession sqlSession=configManager.getSession();
		List boardList=sqlSession.selectList("Board.selectAll");//쿼리 수행 후 list 반환 받기
		configManager.closeSession(sqlSession);
		return boardList;
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Board select(int board_id) {
		SqlSession sqlSession=configManager.getSession();
		Board board=sqlSession.selectOne("Board.select",board_id);
		configManager.closeSession(sqlSession);
		return board;
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
