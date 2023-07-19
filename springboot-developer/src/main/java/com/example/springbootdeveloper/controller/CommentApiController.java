package com.example.springbootdeveloper.controller;

import com.example.springbootdeveloper.domain.Comment;
import com.example.springbootdeveloper.dto.AddCommentRequest;
import com.example.springbootdeveloper.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long articleId, @RequestBody AddCommentRequest request) {
        Comment savedComment = commentService.addComment(articleId, request.getContent());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok()
                .build();
    }
}
