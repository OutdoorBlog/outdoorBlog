package com.example.springbootdeveloper.dto;

import com.example.springbootdeveloper.domain.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private String createdAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt().toString();
    }
}
