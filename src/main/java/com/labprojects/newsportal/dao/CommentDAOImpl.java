package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Comment> getComments() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Comment", Comment.class);
        List<Comment> comments = query.getResultList();
        return comments;
    }
}
