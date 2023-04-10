package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String getNews(Model model) {
        List<News> news = newsService.getNews();
        model.addAttribute("news", news);
        return "news/news";
    }
}
