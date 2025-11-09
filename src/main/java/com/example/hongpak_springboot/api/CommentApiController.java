package com.example.hongpak_springboot.api;

import com.example.hongpak_springboot.dto.CommentDto;
import com.example.hongpak_springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{article_id}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable long article_id) {
        // 1. 서비스에게 위임
        List<CommentDto> dtos = commentService.index(article_id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable long articleId, @RequestBody CommentDto dto) {
        // 1. 서비스에게 위임
        CommentDto creteDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(creteDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable long id, @RequestBody CommentDto dto) {
        // 1. 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable long id) {
        // 1. 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }


}
