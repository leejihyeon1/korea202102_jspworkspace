package com.koreait.tourapp.model.store.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.tourapp.model.domain.Store;
import com.koreait.tourapp.model.mybatis.MybatisConfigManager;

public class MybatisStoreDAO implements StoreDAO{
	MybatisConfigManager configManager=MybatisConfigManager.getInstance();
	@Override
	public int insert(Store store) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.insert("Store.insert",store);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		SqlSession sqlSession=configManager.getSession();
		List list=sqlSession.selectList("Store.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}

	@Override
	public Store select(int store_id) {
		SqlSession sqlSession=configManager.getSession();
		Store store=sqlSession.selectOne("Store.select",store_id);
		configManager.closeSession(sqlSession);
		return store;
	}

	@Override
	public int update(Store store) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int store_id) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.delete("Store.delete",store_id);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public Store getLastStore() {
		SqlSession sqlSession=configManager.getSession();
		int store_id=0;
		Store store=null;
		store_id=sqlSession.selectOne("Store.getLastId");
		if(store_id>0) {
			store=sqlSession.selectOne("Store.getLastStore",store_id);
		}
		configManager.closeSession(sqlSession);
		return store;
	}

}
