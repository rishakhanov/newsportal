package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.dto.CommentDTO;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.Like;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.service.NewsService;
import com.labprojects.newsportal.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<Like> likes = newsService.getLikes(id);
        int likesSize = 0;
        if (likes != null) {
            likesSize = likes.size();
        }
        model.addAttribute("news", newsService.getNews(id));
        model.addAttribute("likesSize", likesSize);
        return "news/show-news";
    }

    @GetMapping("/comments/{id}")
    private String getComments(@PathVariable("id") Long id, Model model) {
        List<Comment> comments = newsService.getComments(id);
        if (comments != null) {
            List<CommentDTO> commentsDTO = newsService.getCommentsDTO(comments);
            model.addAttribute("comments", commentsDTO);
        }
        model.addAttribute("newsId", id);
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
    public String searchNews(String item, Model model) {
        List<News> newsList = null;
        if (item != null) {
            newsList = newsService.getNews(item);
        }
        if (newsList != null) {
            model.addAttribute("news", newsList);
            model.addAttribute("keyword", item);
            return "news/search";
        }
        return "redirect:/";
    }
/*
    @GetMapping("/news/search/{keyword}")
    public String searchNewsByKeyword(@PathVariable("keyword") String keyword, Model model) {
        List<News> newsList = null;
        if (keyword != null) {
            newsList = newsService.getNews(keyword);
        }
        if (newsList != null) {
            model.addAttribute("news", newsList);
            model.addAttribute("keyword", keyword);
            return "news/search";
        }
        return "redirect:/";

    }
*/
    @PostMapping("/news/likes/{id}")
    public String addLike(@PathVariable("id") Long id, Principal principal) {
        Optional<Person> personOptional = null;
        if (principal != null) {
            String username = principal.getName();
            personOptional = personService.getPersonByName(username);
        }
        Person person;

        if (personOptional.isPresent()) {
            person = personOptional.get();
            Long personId = person.getId();
            if (!newsService.personLiked(id, personId)) {
                Like like = new Like();
                like.setNews(newsService.getNews(id));
                like.setPerson(person);
                newsService.saveLike(like);
            }
        }

        return "redirect:/news/{id}?param=" + id;
    }
}
