package com.example.springbootdeveloper.service;

import com.example.springbootdeveloper.domain.Article;
import com.example.springbootdeveloper.domain.Comment;
import com.example.springbootdeveloper.repository.ArticleRepository;
import org.springframework.stereotype.Service;
//import com.example.springbootdeveloper.service.ArticleService;
//import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Other methods and dependencies...

    public void addComment(Long articleId, Comment comment) {
        Article article = findById(articleId);
        article.getComments().add(comment);
        articleRepository.save(article);
    }


}
