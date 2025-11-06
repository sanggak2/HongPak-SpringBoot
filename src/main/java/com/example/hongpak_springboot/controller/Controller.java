package com.example.hongpak_springboot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/hello")
    public String Hello(Model model) {
        model.addAttribute("username", "Bong");
        return "hello"; // templates/hello.mustache -> 브라우저로 전송
    }
}
