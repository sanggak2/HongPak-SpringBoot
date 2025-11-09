package com.example.hongpak_springboot.repository;

import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest    // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글*/
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 예상
            Article article = new Article(4L, "응애", "baby");
            Comment a = new Comment(1L, article, "응애맨", "나 아기");
            Comment b = new Comment(2L, article, "kim", "김부각");
            Comment c = new Comment(3L, article, "jang", "장독대");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }
        /* Case 2 : 1번의 모든 댓글*/
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 예상
            Article article = new Article(1L, "가", "1");
            List<Comment> expected = Arrays.asList();

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }

        /* Case 3 : 9번의 모든 댓글*/
        {
            // 입력 데이터 준비
            Long articleId = 9L;

            // 예상
            Article article = new Article(null, null, null);
            List<Comment> expected = Arrays.asList();

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }

        /* Case 4 : -1번의 모든 댓글*/
        {
            // 입력 데이터 준비
            Long articleId = -1L;

            // 예상
            Article article = new Article(null, null, null);
            List<Comment> expected = Arrays.asList();

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1 : kim의 모든 댓글*/
        {
            // 입력 데이터 준비
            String nickname = "kim";

            // 예상
            Article article4 = new Article(4L, "응애", "baby");
            Article article5 = new Article(5L, "나 애기", "구구가가");
            Article article6 = new Article(6L, "안아줘요", "으악");
            Comment a = new Comment(5L, article5, nickname, "부기부기");
            Comment b = new Comment(2L, article4, nickname, "김부각");
            Comment c = new Comment(9L, article6, nickname, "렛미쉐익더부디");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }
        /* Case 2 : null의 모든 댓글*/
        {
            // 입력 데이터 준비
            String nickname = null;

            // 예상
            List<Comment> expected = Arrays.asList();

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }
        /* Case 3 : "i"의 모든 댓글*/
        {
            // 입력 데이터 준비
            String nickname = "i";

            // 예상
            List<Comment> expected = Arrays.asList();

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 검증
            assertNotNull(expected.toString(), comments.toString());
        }
    }
}