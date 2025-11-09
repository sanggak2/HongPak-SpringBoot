package com.example.hongpak_springboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Restcontroller와 일반 controller와의 차이
 * 일반 컨트롤러 반환 : 뷰 템플릿 페이지
 * 레스트 컨트롤러 반환 : json이나 텍스트
 */
@RestController // JSON 반환하는 컨트롤러
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {

        return "Hello World";
    }
}
