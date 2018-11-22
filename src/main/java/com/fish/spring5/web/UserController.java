package com.fish.spring5.web;

import com.fish.spring5.common.PageResultBean;
import com.fish.spring5.dto.Page;
import com.fish.spring5.common.ResultBean;
import com.fish.spring5.dto.UserDTO;
import com.fish.spring5.query.UserQuery;
import com.fish.spring5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/listPage")
    public String listPage() {
        return "user/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageResultBean<UserDTO> getUses(UserQuery userQuery) {
        try {
            Page<UserDTO> userPage = userService.findUserByPage(userQuery);
            return new PageResultBean(userPage, ResultBean.SUCCESS, ResultBean.SUCCESS_MSG);
        } catch (Exception e) {
            return new PageResultBean(null, 500, "获取用户信息失败::" + e.getMessage());
        }
    }

}
