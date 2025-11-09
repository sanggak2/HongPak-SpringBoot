package com.example.hongpak_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 댓글 엔티티 여러개가 하나의 아티클에 연동
    @JoinColumn(name="article_id")  // Article 하나의 대표값(PK)를 FK하겠다
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;
}
