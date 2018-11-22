package com.fish.spring5.service.impl;

import com.fish.spring5.dao.PermissionDao;
import com.fish.spring5.dto.PermissionDTO;
import com.fish.spring5.entity.Permission;
import com.fish.spring5.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional
    public void addOrUpdatePermission(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setName(permissionDTO.getName());
        permission.setPid(permissionDTO.getPid());
        permission.setUrl(permissionDTO.getUrl());
        permission.setDisabled(false);
        permissionDao.saveOrUpdate(permission);
    }

    @Override
    @Transactional
    public void deletePermission(int id) {
        Permission permission = permissionDao.findById(id);
        if (permission != null) {
            permissionDao.delete(permission);
        }
    }
}
