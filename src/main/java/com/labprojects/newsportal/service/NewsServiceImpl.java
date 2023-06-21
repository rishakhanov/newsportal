package com.labprojects.newsportal.service;

import com.labprojects.newsportal.dao.NewsDAOImpl;
import com.labprojects.newsportal.dao.PersonDAOImpl;
import com.labprojects.newsportal.dto.CommentDTO;
import com.labprojects.newsportal.entity.Comment;
import com.labprojects.newsportal.entity.Like;
import com.labprojects.newsportal.entity.News;
import com.labprojects.newsportal.entity.Person;
import com.labprojects.newsportal.util.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAOImpl newsDAO;

    @Autowired
    private PersonDAOImpl personDAO;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<News> getNews() {
        return newsDAO.getNews();
    }

    @Override
    public News getNews(Long id) {
        return newsDAO.getNews(id);
    }

    @Override
    public List<Comment> getComments(Long id) {
        return newsDAO.getComments(id);
    }

    @Override
    public List<CommentDTO> getCommentsDTO(List<Comment> comments) {
        List<CommentDTO> commentDTO = new ArrayList<>();
        Long id;
        for (Comment comment : comments) {
            id = comment.getPerson().getId();
            Person person = personDAO.getPerson(id);
            commentDTO.add(commentMapper.mapToCommentDTO(comment, person.getUsername()));
        }
        return commentDTO;
    }

    @Override
    @Transactional
    public void updateNews(Long id, News news) {
        newsDAO.updateNews(id, news);
    }

    @Override
    @Transactional
    public void deleteNews(Long id) {
        newsDAO.deleteNews(id);
    }

    @Override
    @Transactional
    public void saveNews(News news) {
        newsDAO.saveNews(news);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        newsDAO.deleteComment(id);
    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        newsDAO.saveComment(comment);
    }

    @Override
    public News getNews(String searchItem) {
        return newsDAO.getNews(searchItem);
    }

    @Override
    public List<Like> getLikes(Long newsId) {
        return newsDAO.getLikes(newsId);
    }

    @Override
    public boolean personLiked(Long newsId, Long personId) {
        List<Like> likes = getLikes(newsId);
        if (likes != null) {
            for (Like like : likes) {
                if (like.getPerson().getId().equals(personId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void saveLike(Like like) {
        newsDAO.saveLike(like);
    }
}
