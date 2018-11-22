package com.fish.spring5.service;

import com.fish.spring5.dto.Page;
import com.fish.spring5.dto.PermissionDTO;
import com.fish.spring5.dto.UserDTO;
import com.fish.spring5.query.UserQuery;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
public interface UserService {

    Page<UserDTO> findUserByPage(UserQuery query);

    UserDTO findUserById(int id);

    List<PermissionDTO> findPermissionsByUserId(int id);

}
