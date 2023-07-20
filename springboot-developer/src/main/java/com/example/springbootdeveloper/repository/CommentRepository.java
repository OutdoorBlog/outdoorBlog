package com.example.springbootdeveloper.repository;

import com.example.springbootdeveloper.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
    Optional<Comment> findByIdAndArticleId(Long commentId, Long articleId);
}
