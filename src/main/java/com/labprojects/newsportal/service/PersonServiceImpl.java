package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.PersonDAOImpl;
import com.labprojects.newsportal.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAOImpl personDAOImpl;

    @Override
    @Transactional
    public List<Person> getPersons() {
        return personDAOImpl.getPersons();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        personDAOImpl.savePerson(person);
    }

    @Override
    @Transactional
    public Person getPerson(Long id) {
        return personDAOImpl.getPerson(id);
    }

    @Override
    @Transactional
    public Optional<Person> getPerson(String email) {
        return personDAOImpl.getPerson(email);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        personDAOImpl.deletePerson(id);
    }
}