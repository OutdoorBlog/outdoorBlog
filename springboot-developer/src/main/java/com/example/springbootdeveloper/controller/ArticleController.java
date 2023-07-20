package com.example.springbootdeveloper.controller;

import com.example.springbootdeveloper.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Other controller methods...

    @PostMapping("/articles/{articleId}/comments")
    public String addComment(@PathVariable Long articleId, @RequestParam String comment) {
        Comment newComment = new Comment();
        newComment.setContent(comment);

        articleService.addComment(articleId, newComment);

        return "redirect:/articles/" + articleId;
    }

    // Other controller methods...
}
