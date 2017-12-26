package com.sendroids.framedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class GeneralController {
    @RequestMapping("/")
    public String index() {
        return "/login";
    }

    @RequestMapping("/index")
    public String index1() {
        return "/opencv";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/third_party")
    public String third_party() { return "case/third_party"; }
}
