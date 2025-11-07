package com.example.hongpak_springboot.repository;

import com.example.hongpak_springboot.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // CrudRepository<Article, Long>이 SpringBoot에서 구현한 CRUD리포지토리.
    // 직접 구현 안해도됨

    @Override
    ArrayList<Article> findAll();
}
