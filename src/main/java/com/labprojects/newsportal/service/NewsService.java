package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dto.CommentDTO;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;

import java.util.List;

public interface NewsService {

    public List<News> getNews();

    public News getNews(Long id);

    public List<Comment> getComments(Long id);

    public List<CommentDTO> getCommentsDTO(List<Comment> comments);

    public void updateNews(Long id, News news);

    public void deleteNews(Long id);

    public void saveNews(News news);

    public void deleteComment(Long id);

    public void saveComment(Comment comment);

    public News getNews(String searchItem);
}
