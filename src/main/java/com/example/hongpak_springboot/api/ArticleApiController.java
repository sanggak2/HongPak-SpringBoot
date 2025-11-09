package com.example.hongpak_springboot.api;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import com.example.hongpak_springboot.service.ArticleService;
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
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> save(@RequestBody ArticleForm dto) {
        Article saved = articleService.create(dto);

        return (saved != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // 트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdlist = articleService.createArticles(dtos);
        return (createdlist != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdlist):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
