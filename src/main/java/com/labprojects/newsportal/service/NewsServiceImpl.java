package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.NewsDAOImpl;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void updateNews(Long id, News news) {
        newsDAO.updateNews(id, news);
    }

    @Override
    @Transactional
    public void deleteNews(Long id) {
        newsDAO.deleteNews(id);
    }

    @Override
    @Transactional
    public void saveNews(News news) {
        newsDAO.saveNews(news);
    }


}
