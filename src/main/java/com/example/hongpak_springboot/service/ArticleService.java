package com.example.hongpak_springboot.service;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service    // 서비스 선언
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) // 생성하는데 아이디가 들어올 필요가 없으니 들어왔으면 null로
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article);
        // 2. 대상 엔티티 찾기
        Article temp = articleRepository.findById(id).orElse(null);
        // 3. 대상 엔티티 없음 | 대상 엔티티 아이디 != urn아이디
        if (temp == null || !id.equals(article.getId())) {
            log.info("잘못된 응답! id: {}, article: {}", id, article);
            return null;
        }
        // 4. 업데이트
        temp.patch(article);
        return articleRepository.save(article);
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article temp = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (temp == null) {
            return null;
        }

        // 3. 대상 삭제
        articleRepository.delete(temp);

        // 4. 데이터 반환
        return temp;
    }

    @Transactional  // 해당 메서드를 트랜잭션화 (실패하면 메서드 실행 전 상태로 롤백)
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto를 entity로
        List<Article> articles = dtos.stream()
                .map(ArticleForm::toEntity)
                .toList();

        // 2. entity를 DB로 저장
        articles.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결제 실패")
        );

        // 4. 결과 반환
        return articles;
    }
}
