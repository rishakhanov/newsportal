package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Person;

import java.util.List;

public interface PersonDAO {

    public List<Person> getPersons();

    public void savePerson(Person person);

    public Person getPerson(Long id);

    public void deletePerson(Long id);
}
