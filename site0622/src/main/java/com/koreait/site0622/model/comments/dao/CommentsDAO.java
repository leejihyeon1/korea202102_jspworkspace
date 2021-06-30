package com.koreait.site0622.model.comments.dao;

import java.util.List;

import com.koreait.site0622.model.domain.Comments;

public interface CommentsDAO {
	//CRUD메서드 정의
	public int insert(Comments comments);
	public List selectAll();
	public Comments select(int comments_id);
	public int update(Comments comments);
	public int delete(int comments_id);
	
	public List selectByNewsId(int news_id);//해당 뉴스에 딸려있는 댓글 목록
}
