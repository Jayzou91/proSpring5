package com.fish.spring5.dao.impl;

import com.fish.spring5.dao.UserDao;
import com.fish.spring5.dao.impl.BaseDaoImpl;
import com.fish.spring5.entity.Role;
import com.fish.spring5.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

    @Override
    public Optional<User> findByEmail(String email) {
        return (Optional<User>) sessionFactory.getCurrentSession()
                .createQuery("from User where email = ?")
                .setParameter(1, email)
                .uniqueResult();
    }

    @Override
    public List<Role> findRolesByUserId(int userId) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery(User.FIND_ROLES_BY_USER_ID)
                .setParameter("id", userId)
                .list();
    }
}
