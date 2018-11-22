package com.fish.spring5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/11
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String doLogin() {
        return "redirect:/admin/index";
    }
}
