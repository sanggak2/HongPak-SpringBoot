package com.example.hongpak_springboot.service;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트와 연동되어 테스팅
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "가", "1");
        Article b = new Article(2L, "나", "2");
        Article c = new Article(3L, "다", "3");
        ArrayList<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_아이디일치() {
        //예상
        Long id = 1L;
        Article expected = new Article(1L, "가", "1");

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_아이디존재X() {
        //예상
        Long id = -1L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_들어옴() {
        //예상
        ArticleForm dto = new ArticleForm(null, "11", "33");
        Article expected = new Article(4L, "11", "33");

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id랑_같이들어옴() {
        //예상
        ArticleForm dto = new ArticleForm(4L, "11", "33");
        Article expected = null;

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_idTitleContent가_있는() {
        //매개변수
        Long id = 1L;
        ArticleForm dto = new ArticleForm(1L, "뷰", "2");
        //예상
        Article expected = new Article(1L, "뷰", "2");

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_idContent만_있는() {
        //매개변수
        Long id = 1L;
        ArticleForm dto = new ArticleForm(1L, null, "2");
        //예상
        Article expected = new Article(1L, null, "2");

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재X_id() {
        //매개변수
        Long id = 100L;
        ArticleForm dto = new ArticleForm(null, null, null);
        //예상
        Article expected = null;

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_실패_id불일치() {
        //매개변수
        Long id = 3L;
        ArticleForm dto = new ArticleForm(1L, "가", "1");
        //예상
        Article expected = null;

        //실제
        Article article = articleService.update(id, dto);

        //비교
        assertEquals(expected, article);
    }


    @Test
    @Transactional
    void delete_성공() {
        //매개변수
        Long id = 1L;
        //예상
        Article expected = new Article(1L, "가", "1");

        //실제
        Article article = articleService.delete(id);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재X_id() {
        //매개변수
        Long id = 100L;
        //예상
        Article expected = null;

        //실제
        Article article = articleService.delete(id);

        //비교
        assertEquals(expected, article);
    }
}