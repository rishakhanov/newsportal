package com.labprojects.newsportal.dto;

import java.time.LocalDate;

public class CommentDTO {

    private Long id;

    private String body;

    private String username;

    private LocalDate createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", username='" + username + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
