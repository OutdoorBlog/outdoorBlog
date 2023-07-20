package com.example.springbootdeveloper.controller;

import com.example.springbootdeveloper.domain.Article;
import com.example.springbootdeveloper.dto.ArticleListViewResponse;
import com.example.springbootdeveloper.dto.ArticleViewResponse;
import com.example.springbootdeveloper.service.BlogService;
import com.example.springbootdeveloper.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
    @GetMapping("/search")
    public String searchArticles(@RequestParam String keyword, Model model) {
        List<ArticleViewResponse> searchResults = blogService.searchArticlesByKeyword(keyword)
                .stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", searchResults);

        return "searchResults";
    }

//    @GetMapping("/articles/{articleId}/comments")
//    public List<CommentResponse> getCommentsForArticle(@PathVariable Long articleId) {
//        Article article = blogService.findById(articleId);
//        List<Comment> comments = commentService.getCommentsForArticle(article);
//        return comments.stream().map(CommentResponse::new).collect(Collectors.toList());
//    }

//    @PostMapping("/articles/{articleId}/comments")
//    public String addComment(@PathVariable Long articleId, @RequestParam String comment) {
//        // Retrieve the article by ID
//        Article article = articleService.findById(articleId);
//
//        // Create a new comment
//        Comment newComment = new Comment();
//        newComment.setArticle(article);
//        newComment.setContent(comment);
//
//        // Save the comment
//        commentService.save(newComment);
//
//        // Redirect to the article page
//        return "redirect:/articles/" + articleId;
//    }

}
