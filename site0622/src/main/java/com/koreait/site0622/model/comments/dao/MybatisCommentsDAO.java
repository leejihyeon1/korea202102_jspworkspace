package com.koreait.site0622.model.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.site0622.model.domain.Comments;
import com.koreait.site0622.model.mybatis.MybatisConfigManager;

public class MybatisCommentsDAO  implements CommentsDAO{
	MybatisConfigManager configManager=MybatisConfigManager.getInstance();
	
	public int insert(Comments comments) {
		SqlSession sqlSession = configManager.getSession(); //쿼리수행 객체 얻기 
		int result = sqlSession.insert("Comments.insert", comments);
		sqlSession.commit(); //DML이라서...
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comments select(int comments_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Comments comments) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int comments_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List selectByNewsId(int news_id) {
		SqlSession sqlSession=configManager.getSession();
		List list=sqlSession.selectList("Comments.selectById", news_id);
		configManager.closeSession(sqlSession);
		return list;
	}

}