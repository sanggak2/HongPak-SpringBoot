package com.example.hongpak_springboot.controller;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired  // springboot에서 싱글턴으로 구현한 객체에 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form);

        // 1. dto를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article);

        // 2. repository에게 엔티티를 db안에 넣어줘 시전.
        Article saved = articleRepository.save(article);
        System.out.println(saved);
        return "";
    }
}
