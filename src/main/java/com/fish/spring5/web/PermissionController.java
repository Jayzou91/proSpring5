package com.fish.spring5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @GetMapping("/listPage")
    public String listPage() {
        return "permission/list";
    }
}
