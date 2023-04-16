package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<News> getNews() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from News ", News.class);
        List<News> news = query.getResultList();
        return news;
    }

    @Override
    public News getNews(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(News.class, id);
    }

    @Override
    public List<Comment> getComments(Long id) {
        News news = getNews(id);
        List<Comment> comments = news.getComments();
        //comments.size();
        //System.out.println(comments);
        return comments;
    }


}