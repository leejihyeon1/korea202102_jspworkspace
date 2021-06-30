package com.korea.stuedy0622.model.member.dao;

import java.awt.List;

import org.apache.ibatis.session.SqlSession;

import com.korea.study0622.model.domain.Member;

import MybatisConfigManager.MybatisConfigManager;

public class MybatisMemberDAO implements MemberDAO{
	MybatisConfigManager configManager;

	
	public MybatisMemberDAO() {
		//session 객체를 얻어올 수 있는 객체 생성
		configManager=MybatisConfigManager.getInstance();
	}
	//멤버 하나 조회
	public Member getMemberId(String user_id) {
		Member member=null;
		SqlSession sqlSession=configManager.getSession();
		member=sqlSession.selectOne("getMemberId",user_id);
		configManager.closeSession(sqlSession);
		return member;
		
		
	}

	@Override
	public int regist(Member member) {
		SqlSession sqlSession=configManager.getSession();
		int result=sqlSession.insert("Member.regist",member);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Member select(Member member) {
		SqlSession sqlSession=configManager.getSession();
		Member obj=sqlSession.selectOne("Member.selectByMember",member);
		configManager.closeSession(sqlSession);
		return obj;
	}

	@Override
	public int delete(int member_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Member selectByKakao(String user_id) {
		SqlSession sqlSession=configManager.getSession();
		Member member=sqlSession.selectOne("Member.selectByKakao",user_id);
		configManager.closeSession(sqlSession);
		return member;
	}

}
