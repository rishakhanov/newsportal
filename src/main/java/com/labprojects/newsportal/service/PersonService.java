package com.labprojects.newsportal.service;

import com.labprojects.newsportal.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<Person> getPersons();

    public void savePerson(Person person);

    public void updatePerson(Long id, Person person);

    public Person getPerson(Long id);

    public Optional<Person> getPerson(String email);

    public Optional<Person> getPersonByName(String username);

    void deletePerson(Long id);
}
