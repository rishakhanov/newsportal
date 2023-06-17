package com.labprojects.newsportal.util;

import com.labprojects.newsportal.dto.CommentDTO;
import com.labprojects.newsportal.entity.Comment;
import org.springframework.stereotype.Component;
@Component
public class CommentMapper {
    public CommentDTO mapToCommentDTO(Comment comment, String username) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        commentDTO.setUsername(username);
        commentDTO.setCreatedDate(comment.getCreatedDate());
        return commentDTO;
    }
}
