package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.NewsDAOImpl;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAOImpl newsDAO;

    @Override
    public List<News> getNews() {
        return newsDAO.getNews();
    }

    @Override
    public News getNews(Long id) {
        return newsDAO.getNews(id);
    }

    @Override
    public List<Comment> getComments(Long id) {
        return newsDAO.getComments(id);
    }


}
