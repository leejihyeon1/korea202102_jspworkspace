package com.koreait.model2app.model.member.dao;

import java.util.List;

import com.koreait.model2app.model.domain.Member;

//모든~ 하위 memberdao가 반드시 구현해야할 메서드 정의
public interface MemberDAO {
	public int insert(Member member);
	public List selectAll();
	public Member select(int member_id);
	public int update(Member member);
	public int delete(int member_id);

}
