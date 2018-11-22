package com.fish.spring5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/11
 */
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String login() {
        return "logout";
    }
}
