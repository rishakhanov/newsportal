package com.labprojects.newsportal.dao;

import com.labprojects.newsportal.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    public List<Person> getPersons();

    public void savePerson(Person person);

    public void updatePerson(Long id, Person person);

    public Person getPerson(Long id);

    public Optional<Person> getPerson(String email);

    public void deletePerson(Long id);


}
