package com.fish.spring5.web;

import com.fish.spring5.vo.RoleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/listPage")
    public String listPage(Model model) {
        List<RoleVO> roles = new ArrayList<>();
        RoleVO role = new RoleVO();
        role.setId(1);
        role.setName("超级管理员");
        role.setUsers("张三,李四,王五");
        role.setDescription("拥有超级权利");
        roles.add(role);
        model.addAttribute("roles", roles);
        return "role/list";
    }

    @GetMapping("/add")
    public String add() {
        return "role/add";
    }
}
