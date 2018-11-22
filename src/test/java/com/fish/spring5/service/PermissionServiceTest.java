package com.fish.spring5.service;

import com.fish.spring5.BaseTest;
import com.fish.spring5.dto.PermissionDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public class PermissionServiceTest extends BaseTest {

    private PermissionService permissionService;

    @Before
    public void setUp() {
        permissionService = context.getBean(PermissionService.class);
    }

    @Test
    public void addOrUpdatePermission() {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setName("资讯管理");
        permissionDTO.setUrl("/news/listPage");
        permissionDTO.setPid(0);
        permissionService.addOrUpdatePermission(permissionDTO);
    }

    @Test
    public void deletePermission() {
    }

    @After
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

}