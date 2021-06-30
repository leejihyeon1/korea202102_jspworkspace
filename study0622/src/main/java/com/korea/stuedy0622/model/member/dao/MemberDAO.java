package com.korea.stuedy0622.model.member.dao;

import java.awt.List;

import com.korea.study0622.model.domain.Member;

public interface MemberDAO {
	public Member getMemberId(String user_id);
	public int regist(Member member);
	public List selectAll();
	public Member select(int member_id);
	public Member select(Member member);
	public int delete(int member_id);
	public Member selectByKakao(String user_id);
}
