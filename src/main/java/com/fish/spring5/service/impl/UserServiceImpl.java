package com.fish.spring5.service.impl;

import com.fish.spring5.dao.PermissionDao;
import com.fish.spring5.dto.Page;
import com.fish.spring5.dao.UserDao;
import com.fish.spring5.dto.PermissionDTO;
import com.fish.spring5.dto.UserDTO;
import com.fish.spring5.entity.Permission;
import com.fish.spring5.entity.Role;
import com.fish.spring5.entity.User;
import com.fish.spring5.query.UserQuery;
import com.fish.spring5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findUserByPage(UserQuery query) {
        List<User> users = userDao.findAll();
        Page<UserDTO> result = new Page<>();
        List<UserDTO> datas = new ArrayList<>(users.size());
        for (User user : users) {
            datas.add(toUserDTO(user));
        }
        result.setRows(datas);
        result.setTotal(users.size());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUserById(int id) {
        User user = userDao.findById(id);
        return toUserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionDTO> findPermissionsByUserId(int id) {
        List<Role> roles = userDao.findRolesByUserId(id);
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissions = permissionDao.findPermissionsByRoleIds(roleIds);
        if (permissions == null || permissions.isEmpty()) {
            return Collections.emptyList();
        }

        Iterator<Permission> iter = permissions.iterator();
        List<PermissionDTO> result = new ArrayList<>();
        while (iter.hasNext()) {
            Permission permission = iter.next();
            if (permission.getPid().intValue() == 0) {
                result.add(new PermissionDTO(permission));
                iter.remove();
            }
        }

        for (PermissionDTO parent : result) {
            int pid = parent.getId();
            for (Permission child : permissions) {
                if (child.getPid().intValue() == pid) {
                    parent.getChildren().add(new PermissionDTO(child));
                }
            }
        }
        return result;
    }

    private UserDTO toUserDTO(User user) {
        UserDTO result = new UserDTO();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setGender(user.getGender().name());
        return result;
    }


}
