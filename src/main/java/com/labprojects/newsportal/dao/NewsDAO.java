package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;

import java.util.List;

public interface NewsDAO {

    public List<News> getNews();

    public News getNews(Long id);

    public List<Comment> getComments(Long id);
}
