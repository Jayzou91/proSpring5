package com.fish.spring5.service;

import com.fish.spring5.dto.PermissionDTO;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public interface PermissionService {

    void addOrUpdatePermission(PermissionDTO permissionDTO);

    void deletePermission(int id);
}
