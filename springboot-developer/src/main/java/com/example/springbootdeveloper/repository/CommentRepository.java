package com.example.springbootdeveloper.repository;

import com.example.springbootdeveloper.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 댓글 관련 추가적인 쿼리가 필요한 경우 여기에 작성할 수 있습니다.
}
