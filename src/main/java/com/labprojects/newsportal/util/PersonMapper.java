package com.labprojects.newsportal.util;

import com.labprojects.newsportal.dto.PersonDTO;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.Like;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {

    private final RoleService roleService;

    @Autowired
    public PersonMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public Person mapToPersonEntity(PersonDTO personDTO) {
        Person person = new Person();

        List<News> news = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();

        person.setRole(roleService.getRole(4L));
        person.setNews(news);
        person.setComments(comments);
        person.setLikes(likes);
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setEmail(personDTO.getEmail());
        person.setBaned(false);

        return person;
    }
}