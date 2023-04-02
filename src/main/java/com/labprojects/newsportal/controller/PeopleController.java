package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.service.PersonService;
import com.labprojects.newsportal.service.PersonServiceImpl;
import com.labprojects.newsportal.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String getPeople(Model model) {
        List<Person> allPersons = personService.getPersons();
        model.addAttribute("allPersons", allPersons);
        return "people/all-persons";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/show-person";
    }

    @GetMapping("/new")
    public String showAddForm(@ModelAttribute("person") Person person) {
        return "people/add-person";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/add-person";
        }

        personService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                               @PathVariable("id") Long id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        //personService.savePerson(person);
        personService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
