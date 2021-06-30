package com.korea.stuedy0622.model.news.dao;

import java.util.List;

import com.korea.study0622.model.domain.News;

public interface NewsDAO {
	public int insert(News news);
	public List selectAll();
	public News select(int news_id);
	public int update(News news);
	public int delete(int news_id);
}
