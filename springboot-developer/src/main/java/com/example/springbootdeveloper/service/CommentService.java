package com.example.springbootdeveloper.service;

import com.example.springbootdeveloper.domain.Article;
import com.example.springbootdeveloper.domain.Comment;
import com.example.springbootdeveloper.dto.AddCommentRequest;
import com.example.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(Article article, AddCommentRequest request, String author) {
        Comment comment = new Comment(article, author, request.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Article article, long commentId, AddCommentRequest request, String author) {
        Comment comment = commentRepository.findByIdAndArticleId(commentId, article.getId())
                .orElseThrow(() -> new IllegalArgumentException("not found : " + commentId));

        authorizeCommentAuthor(comment, author);
        comment.update(request.getContent());

        return comment;
    }

    @Transactional
    public void delete(Article article, long commentId, String author) {
        Comment comment = commentRepository.findByIdAndArticleId(commentId, article.getId())
                .orElseThrow(() -> new IllegalArgumentException("not found : " + commentId));

        authorizeCommentAuthor(comment, author);
        commentRepository.delete(comment);
    }

    // 댓글 작성자인지 확인
    private static void authorizeCommentAuthor(Comment comment, String author) {
        if (!comment.getAuthor().equals(author)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}