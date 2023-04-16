package com.labprojects.newsportal.controller;

import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.service.CommentService;
import com.labprojects.newsportal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }


}
