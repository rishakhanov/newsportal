package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.service.NewsService;
import com.labprojects.newsportal.service.PersonService;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final PersonService personService;

    @Autowired
    public NewsController(NewsService newsService, PersonService personService) {
        this.newsService = newsService;
        this.personService = personService;
    }

    @GetMapping
    public String getNews(Model model) {
        List<News> news = newsService.getNews();
        model.addAttribute("news", news);
        return "news/news";
    }

    @GetMapping("/news/{id}")
    public String getNews(@PathVariable("id") Long id, Model model) {
        model.addAttribute("news", newsService.getNews(id));
        return "news/show-news";
    }

    @GetMapping("/comments/{id}")
    private String getComments(@PathVariable("id") Long id, Model model) {
        List<Comment> comments = newsService.getComments(id);
        model.addAttribute("comments", comments);
        model.addAttribute("newsId", id);
        return "comments/comments";
    }

    @GetMapping("/news/management")
    public String getNewsManagement(Model model) {
        List<News> news = newsService.getNews();
        model.addAttribute("news", news);
        return "news/news-management";
    }

    @GetMapping("news/edit/{id}")
    public String getNewsEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("news", newsService.getNews(id));
        return "news/edit-news";
    }

    @PostMapping("news/edit/{id}")
    public String submitEditedNews(@ModelAttribute News news, Model model, @PathVariable("id") Long id) {
        News newsToBeUpdated = newsService.getNews(id);
        newsToBeUpdated.setContent(news.getContent());
        newsToBeUpdated.setTitle(news.getTitle());
        newsService.updateNews(id, newsToBeUpdated);
        model.addAttribute("news", news);
        return "news/show-news";
    }

    @PostMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return "redirect:/news/management";
    }

    @GetMapping("/news/new")
    public String showAddNewsForm(@ModelAttribute("news") News news) {
        return "news/add-news";
    }

    @PostMapping("/news")
    public String addNews(@ModelAttribute News news, Principal principal) {
        String username = principal.getName();
        News newNews = new News();
        newNews.setTitle(news.getTitle());
        newNews.setContent(news.getContent());
        newNews.setCreatedDate(LocalDate.now());

        Optional<Person> personOptional = personService.getPersonByName(username);
        Person person;

        if (personOptional.isPresent()) {
            person = personOptional.get();
            newNews.setPerson(person);
            newsService.saveNews(newNews);
        }

        return "redirect:/news/management";
    }
}
