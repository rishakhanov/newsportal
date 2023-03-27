package com.labprojects.newsportal.util;

import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personOptional = personService.getPerson(person.getEmail());

        if ((personOptional.isPresent() && (personOptional.get().getEmail().equals(person.getEmail())))
                &&(!personOptional.get().getId().equals(person.getId()))) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
