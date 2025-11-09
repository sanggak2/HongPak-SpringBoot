package com.example.hongpak_springboot.entity;

import com.example.hongpak_springboot.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

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

    public static Comment createComment(CommentDto dto, Article article) {
        // 1. 예외처리
        if(dto.getId() != null)
            throw new  IllegalArgumentException("댓글 생성 실패! 생성 시 댓글의 id가 없어야합니다");
        if(!Objects.equals(dto.getArticle_id(), article.getId()))
            throw new  IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다");

        // 2. 생성 및 변환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 1. 예외 발생
        if(!Objects.equals(this.id, dto.getId()))
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id입력.");

        // 객체 갱신
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
