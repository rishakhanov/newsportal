package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.PersonDAO;
import com.labprojects.newsportal.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    @Transactional
    public List<Person> getPersons() {
        return personDAO.getPersons();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    @Transactional
    public Person getPerson(Long id) {
        return personDAO.getPerson(id);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        personDAO.deletePerson(id);
    }
}
