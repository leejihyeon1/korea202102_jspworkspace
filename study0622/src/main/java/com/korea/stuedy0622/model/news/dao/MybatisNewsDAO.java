package com.korea.stuedy0622.model.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.korea.study0622.model.domain.News;

import MybatisConfigManager.MybatisConfigManager;

public class MybatisNewsDAO implements NewsDAO{
	MybatisConfigManager configManager;
	
	public MybatisNewsDAO() {
		configManager=MybatisConfigManager.getInstance();
	}
	
	@Override
	public int insert(News news) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.insert("News.insert",news);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		SqlSession sqlSession=configManager.getSession();
		List list=sqlSession.selectList("News.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}

	@Override
	public News select(int news_id) {
		SqlSession sqlSession=configManager.getSession();
		News news=sqlSession.selectOne("News.select", news_id);
		configManager.closeSession(sqlSession);
		return news;
	}

	@Override
	public int update(News news) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.update("News.update",news);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public int delete(int news_id) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.delete("News.delete",news_id);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

}
