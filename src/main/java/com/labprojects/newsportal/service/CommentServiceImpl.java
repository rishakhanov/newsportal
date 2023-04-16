package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.CommentDAOImpl;
import com.labprojects.newsportal.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAOImpl commentDAO;

    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }
}
