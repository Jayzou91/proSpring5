package com.fish.spring5.service;

import com.fish.spring5.BaseTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/11
 */
public class UserServiceTest extends BaseTest {


    @Test
    public void findUserByPage() {
        context.getBean(UserService.class).findUserByPage(null);
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void findPermissionsByUserId() {
        context.getBean(UserService.class).findPermissionsByUserId(1);
    }
}