package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import org.hibernate.Hibernate;
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
        //News news = session.get(News.class, id);
        //Hibernate.initialize(news.getComments());
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "SELECT n FROM News n JOIN FETCH n.comments WHERE n.id = " + id;
            Query query = session.createQuery(hql, News.class);
            News news = (News) query.getSingleResult();
            return news.getComments();
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public void updateNews(Long id, News news) {
        Session session = sessionFactory.getCurrentSession();
        News newsToBeUpdated = session.get(News.class, id);
        newsToBeUpdated.setContent(news.getContent());
        newsToBeUpdated.setTitle(news.getTitle());
    }

    @Override
    public void deleteNews(Long id) {
        Session session = sessionFactory.getCurrentSession();
        News news = getNews(id);
        session.delete(news);
    }


    @Override
    public void saveNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        session.save(news);
    }

    @Override
    public void deleteComment(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        session.delete(comment);
    }

    @Override
    public void saveComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
    }

    @Override
    public News getNews(String searchItem) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "SELECT news FROM News news WHERE news.title like" + "'%" + searchItem + "%'";
            Query query = session.createQuery(hql, News.class);
            News news = (News) query.getSingleResult();
            System.out.println(news.getTitle());
            return news;
        } catch (Exception ex) {
            return null;
        }
    }

}
