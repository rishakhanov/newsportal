package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.dto.CommentDTO;
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
        News news = newsService.getNews(id);
        List<Comment> comments = newsService.getComments(id);
        if (comments != null) {
            List<CommentDTO> commentsDTO = newsService.getCommentsDTO(comments);
            model.addAttribute("comments", commentsDTO);
            model.addAttribute("newsId", id);
        }
        model.addAttribute("news", newsService.getNews(id));
        return "comments/comments";
    }

    @PostMapping("/news/{id}/comments/{commentId}")
    public String deleteComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId) {
        newsService.deleteComment(commentId);
        return "redirect:/comments/{id}?param=" + id;
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
        return "redirect:/news/{id}?param=" + id;
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

    @GetMapping("/comments/new/{id}")
    public String showAddCommentForm(@PathVariable("id") Long id, Model model) {
    Comment comment = new Comment();
    model.addAttribute("comment", comment);
    model.addAttribute("newsId", id);
    return "comments/add-comment";
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

    @PostMapping("/comments/new/{id}")
    public String addComment(@ModelAttribute Comment comment, @PathVariable("id") Long id, Principal principal) {
        String username = principal.getName();
        Comment newComment = new Comment();
        newComment.setBody(comment.getBody());
        newComment.setCreatedDate(LocalDate.now());
        newComment.setNews(newsService.getNews(id));

        Optional<Person> personOptional = personService.getPersonByName(username);
        Person person;

        if (personOptional.isPresent()) {
            person = personOptional.get();
            newComment.setPerson(person);
            newsService.saveComment(newComment);
        }

        return "redirect:/comments/{id}?param=" + id;
    }

    @PostMapping("/news/search")
    public String searchItem(String item) {
        System.out.println(item);
        Long id = newsService.getNews(item).getId();
        System.out.println(id);

        return "redirect:/news/"+ id;
    }
}
