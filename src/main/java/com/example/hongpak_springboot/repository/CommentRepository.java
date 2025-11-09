package com.example.hongpak_springboot.repository;

import com.example.hongpak_springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글 모든 댓글 조회
    @Query(value =
            "SELECT * FROM comment WHERE article_id = :articleId"
            , nativeQuery = true)  // @Query : 이 함수가 실행되면 실행될 쿼리 작성
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임 모들 댓글 조회  >> resources\META-INF\orm.xml
    List<Comment> findByNickname(String nickname);
}
