package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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
        session.save(person);
    }

    @Override
    public void updatePerson(Long id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);
        personToBeUpdated.setUsername(person.getUsername());
        personToBeUpdated.setEmail(person.getEmail());
        personToBeUpdated.setRole(person.getRole());
        personToBeUpdated.setPassword(person.getPassword());
    }

    @Override
    public Person getPerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Override
    public Optional<Person> getPerson(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Person where email = :email");
        query.setParameter("email", email);
        List<Person> list = query.getResultList();
        return list.size() > 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
    }

    @Override
    public Optional<Person> getPersonByName(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Person where username = :username");
        query.setParameter("username", username);
        List<Person> list = query.getResultList();
        return list.size() > 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
    }

    @Override
    public void deletePerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = getPerson(id);
        session.delete(person);
    }

    @Override
    public void changePersonStatus(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeBannedOrPermitted = session.get(Person.class, id);
        Boolean status = personToBeBannedOrPermitted.isEnabled();
        personToBeBannedOrPermitted.setEnabled(!status);
    }


}
