package com.korea.stuedy0622.model.board.dao;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.korea.study0622.model.domain.Board;

import MybatisConfigManager.MybatisConfigManager;

public class MybatisBoardDAO implements BoardDAO{
	MybatisConfigManager configManager;
	
	public MybatisBoardDAO() {
		configManager=MybatisConfigManager.getInstance();
	}
	
	@Override
	public int insert(Board board) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.insert("Board.insert",board);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		SqlSession sqlSession=configManager.getSession();
		List list=sqlSession.selectList("selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}

	@Override
	public Board select(int board_id) {
		SqlSession sqlSession=configManager.getSession();
		Board board=sqlSession.selectOne("select",board_id);
		configManager.closeSession(sqlSession);
		return board;
	}

	@Override
	public int update(Board board) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.update("update",board);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public int delete(int board_id) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.delete("delete",board_id);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

}
