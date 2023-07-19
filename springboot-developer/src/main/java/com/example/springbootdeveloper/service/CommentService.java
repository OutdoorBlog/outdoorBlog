package com.example.springbootdeveloper.service;

import com.example.springbootdeveloper.domain.Article;
import com.example.springbootdeveloper.domain.Comment;
import com.example.springbootdeveloper.domain.User;
import com.example.springbootdeveloper.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogService blogService;
    private final UserService userService;

    @Transactional
    public Comment addComment(Long articleId, String content) {
        User currentUser = getCurrentUser();
        Article article = blogService.findById(articleId);

        Comment comment = new Comment(content, article, currentUser);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + commentId));

        authorizeCommentAuthor(comment);
        commentRepository.delete(comment);
    }

    // 댓글을 작성한 유저인지 확인
    private void authorizeCommentAuthor(Comment comment) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!comment.getUser().getUsername().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

    // 현재 로그인한 유저 정보 가져오기
    private User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(userName);
    }
}
