package com.example.hongpak_springboot.dto;

import com.example.hongpak_springboot.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//form데이터를 받아올 그릇

/**
 * lombok을 통한 리팩토링
 */
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;


    public Article toEntity(){
        return new Article(null, title, content);
    }
}
