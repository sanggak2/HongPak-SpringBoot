package com.example.hongpak_springboot.api;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article save(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article);

        // 2. 대상 엔티티 조회
        Article temp = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상없음, id가 다른경우)
        if (temp == null || !id.equals(article.getId())) {
            // 400번 응답
            log.info("잘못된 응답! id: {}, article: {}", id, article);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(article);
        }

        // 4. 업데이트 및 응답
        temp.patch(article);
        Article updated = articleRepository.save(temp);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article temp = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (temp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // 3. 대상 삭제
        articleRepository.delete(temp);

        // 4. 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
