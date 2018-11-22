package com.fish.spring5.dao;

import com.fish.spring5.entity.Role;
import com.fish.spring5.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
public interface UserDao extends BaseDao<User, Integer> {

    Optional<User> findByEmail(String email);

    List<Role> findRolesByUserId(int userId);
}
