package com.koreait.site0625.model.reboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.site0625.model.domain.ReBoard;
import com.koreait.site0625.model.mybatis.MybatisConfigManager;

public class MybatisReBoardDAO implements ReBoardDAO{
	MybatisConfigManager configManager=MybatisConfigManager.getInstance();

	public int insert(ReBoard reboard) {
		SqlSession sqlSession=configManager.getSession();
		System.out.println("넣기 전 아이디 "+reboard.getReboard_id());
		
		int result=sqlSession.insert("ReBoard.insert",reboard);//게시물 넣고 
		reboard.setTeam(reboard.getReboard_id());//얻어진 pk를 team에 적용하기
		sqlSession.update("ReBoard.updateTeam",reboard);//team수정
		
		sqlSession.commit();
		System.out.println("넣은 후 아이디 "+reboard.getReboard_id());
		
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		SqlSession sqlSession=configManager.getSession();
		List list=sqlSession.selectList("ReBoard.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}

	@Override
	public ReBoard select(int board_id) {
		SqlSession sqlSession=configManager.getSession();
		ReBoard reboard=sqlSession.selectOne("ReBoard.select",board_id);
		configManager.closeSession(sqlSession);
		return reboard;
	}

	@Override
	public int update(ReBoard reboard) {
		return 0;
	}

	@Override
	public int delete(int board_id) {
		// TODO Auto-generated method stub
		return 0;
	}


	//답변 등록
	public int reply(ReBoard reboard) {
		SqlSession sqlSession = configManager.getSession();//세션얻기
		
		//result값의 의미? 이 쿼리문에 의해 영향을 받은 레코드 수 반환 (에러를 말하는 게아님f)
		int result=0;
		try {
			sqlSession.update("ReBoard.updateStep", reboard); //자리확보
			//따라서 개발자가 따져보아야할 사항은 result 가 아닌, 에러가 나지 않은 경우에 아래의 답변을 등록해야 한다는 것임..
			sqlSession.update("ReBoard.reply", reboard);//답변등록
			sqlSession.commit();//DML 이라서..
			result=1;
		}catch(Exception e) {
			e.printStackTrace();
			result=0;
		}
		configManager.closeSession(sqlSession);
		return result;
	}

}
