package com.example.springbootdeveloper.controller;

import com.example.springbootdeveloper.domain.Article;
import com.example.springbootdeveloper.domain.Comment;
import com.example.springbootdeveloper.dto.AddCommentRequest;
import com.example.springbootdeveloper.service.ArticleService;
import com.example.springbootdeveloper.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable long articleId,
                                              @RequestBody AddCommentRequest request,
                                              Principal principal) {
        Article article = articleService.findById(articleId);
        Comment savedComment = commentService.save(article, request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }

    @PutMapping("/api/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable long articleId,
                                                 @PathVariable long commentId,
                                                 @RequestBody AddCommentRequest request,
                                                 Principal principal) {
        Article article = articleService.findById(articleId);
        Comment updatedComment = commentService.update(article, commentId, request, principal.getName());

        return ResponseEntity.ok()
                .body(updatedComment);
    }

    @DeleteMapping("/api/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable long articleId,
                                              @PathVariable long commentId,
                                              Principal principal) {
        Article article = articleService.findById(articleId);
        commentService.delete(article, commentId, principal.getName());

        return ResponseEntity.ok()
                .build();
    }
}
