package com.labprojects.newsportal.service;

import com.labprojects.newsportal.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> getPersons();

    public void savePerson(Person person);

    public Person getPerson(Long id);

    void deletePerson(Long id);
}
