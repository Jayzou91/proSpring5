package com.fish.spring5.dao.impl;

import com.fish.spring5.dao.PermissionDao;
import com.fish.spring5.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission, Integer> implements PermissionDao {


    @Override
    public List<Permission> findPermissionsByRoleIds(List<Integer> roleIds) {
        return sessionFactory.getCurrentSession().
                getNamedQuery(Permission.FIND_BY_ROLE_IDS).setParameterList("roleIds", roleIds).list();
    }
}
