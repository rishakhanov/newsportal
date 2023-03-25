package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> getPersons() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Person", Person.class);
        List<Person> allPeople = query.getResultList();
        return allPeople;
    }

    @Override
    public void savePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public Person getPerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Override
    public void deletePerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = getPerson(id);
        session.delete(person);
    }


//    @Override
//    public void updatePerson(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(person);
//    }
}
