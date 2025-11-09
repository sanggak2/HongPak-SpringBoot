package com.example.hongpak_springboot.dto;

import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") // json에서 post로 받아올 때 속성이름. 디폴트는 변수명(여기선 의미없음)
    private Long article_id;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }


}
