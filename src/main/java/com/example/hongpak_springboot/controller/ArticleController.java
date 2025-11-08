package com.example.hongpak_springboot.controller;

import com.example.hongpak_springboot.dto.ArticleForm;
import com.example.hongpak_springboot.entity.Article;
import com.example.hongpak_springboot.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
public class ArticleController {

    @Autowired  // springboot에서 싱글턴으로 구현한 객체에 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

//        System.out.println(form); --> 로깅으로 대체
        log.info(form.toString());

        // 1. dto를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. repository에게 엔티티를 db안에 넣어줘 시전.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){  //@PathVariable url에서 {id}받아오기
        log.info("id : "+id);

        // 1. id로 데이터를 가져옴  Controller
        Article entity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록   Model
        model.addAttribute("article", entity);

        // 3. 보여줄 페이지 설정    View
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 article을 가져온다
        List<Article> entityList = articleRepository.findAll();

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("articleList", entityList);

        // 3. 뷰 페이지 설정
        return "articles/index"; // articles
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(Model model, @PathVariable Long id){ //@PathVariable 변수명과 매핑{변수명} 같아야됨
        // 1. 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터를 등록
        model.addAttribute("article", article);

        // 3. 뷰페이지에 설정
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        // 1. DTO를 엔티티로
        Article entity = form.toEntity();
        log.info(entity.toString());

        // 2. 엔티티를 DB로 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(entity.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 수정한다.
        if(target != null)
            articleRepository.save(entity); // 엔티티 DB로 갱신됨.

        // 3. 수정결과 페이지로
        return "redirect:/articles/"+entity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        log.info("삭제요청");
        // 1. 삭제대상 가져오기(repository)
        Article article = articleRepository.findById(id).orElse(null);
        log.info(Objects.requireNonNull(article).toString());

        // 2. 대상을 삭제한다
        if(article != null){
            articleRepository.delete(article);
            redirectAttributes.addFlashAttribute("msg", "삭제완료");
        }

        // 3. 결과 페이지로 리다이렉트한다
        return "redirect:/articles";
    }
}
