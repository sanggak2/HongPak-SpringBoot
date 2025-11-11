package com.example.hongpak_springboot.service;

import com.example.hongpak_springboot.dto.CommentDto;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.entity.Comment;
import com.example.hongpak_springboot.repository.ArticleRepository;
import com.example.hongpak_springboot.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> show(long articleId) {
        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 2. 엔티티 -> Dto로 변환
//        List<CommentDto> commentDtos = new ArrayList<>();
//        for (Comment comment : comments) {
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            commentDtos.add(dto);
//        }

        // 3. Dto리스트 출력 ::stream사용
        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(long articleId, CommentDto dto) {
        log.info("입력값 => {}", articleId);
        log.info("입력값 => {}", dto);
        // 1. 게시글 조회 및 예외발생
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new IllegalArgumentException("댓글 생성실패. 대상 게시글이 없습니다."));

        // 2. 댓글 엔티티 생성
        Comment createComment = Comment.createComment(dto, article);

        // 3. 댓글을 DB로 저장
        Comment created = commentRepository.save(createComment);

        // 4. DTO로 바꿔서 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(long id, CommentDto dto) {
        // 1. 댓글조회 및 예외발생
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("대상 댓글이 없습니다!"));

        // 2. 댓글 수정
        target.patch(dto);

        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);

        // 4. DTO로 반환
        return CommentDto.createCommentDto(updated);

    }

    @Transactional
    public CommentDto delete(long id) {
        // 1. 댓글조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 없습니다!"));

        // 2. 댓글 삭제
        commentRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
