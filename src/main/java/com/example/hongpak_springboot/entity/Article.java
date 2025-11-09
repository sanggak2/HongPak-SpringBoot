package com.example.hongpak_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //엔티티 선언(이 클래스를 SQL로 DB에 테이블을 만들겠다)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strategy = GenerationType.IDENTITY : DB가 알아서 id생성(더미데이터 아이디 중복문제)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }

}
