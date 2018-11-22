package com.fish.spring5.dao;

import com.fish.spring5.entity.Permission;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public interface PermissionDao extends BaseDao<Permission, Integer> {

    List<Permission> findPermissionsByRoleIds(List<Integer> roleIds);
}
