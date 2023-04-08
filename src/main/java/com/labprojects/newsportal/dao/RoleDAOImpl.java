package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }
}
