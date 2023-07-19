package com.example.springbootdeveloper.repository;

import com.example.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTitleContainingIgnoreCase(String keyword);
}
