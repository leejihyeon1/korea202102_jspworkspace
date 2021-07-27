package com.koreait.springmvc0714.model.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.springmvc0714.exception.DMLException;
import com.koreait.springmvc0714.model.board.repository.BoardDAO;
import com.koreait.springmvc0714.model.domain.Board;
import com.koreait.springmvc0714.model.mybatis.MybatisConfigManager;

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
	public void insert(Board board) throws DMLException{//여기서 에러를 처리해버리면
		//미궁에 빠짐... 뷰 단까지 에러의 원인을 전달해야 한다.. 그래야 사용자들이 에러가 났음을 이해하고,
		//개발자는 적절한 에러 처리를 할 수 있다(에러페이지로 이동)
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.insert("Board.insert",board); //등록되지 않았을 경우 에러가 날 수 있다, 비정상 종료가 됨을 방지
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		if(result==0) {
		throw new DMLException("등록실패");
		}
	
	}

	@Override
	public Board select(int board_id) {
		SqlSession sqlSession=configManager.getSession();
		Board board=sqlSession.selectOne("Board.select",board_id);
		configManager.closeSession(sqlSession);
		return board;
	}

	@Override
	public void update(Board board) throws DMLException{
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.update("Board.update",board);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		if(result==0) {
			throw new DMLException("수정 실패");
		}
		
	}

	@Override
	public void delete(int board_id) throws DMLException{
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.delete("Board.delete",board_id);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		if(result==0) {
			throw new DMLException("삭제 실패");
		}
	}

}
