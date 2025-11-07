package com.example.hongpak_springboot.controller;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired  // springboot에서 싱글턴으로 구현한 객체에 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

//        System.out.println(form); --> 로깅으로 대체
        log.info(form.toString());

        // 1. dto를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. repository에게 엔티티를 db안에 넣어줘 시전.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }
}
