package com.fish.spring5.dao;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
public interface BaseDao<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T saveOrUpdate(T entity);

    void delete(T entity);
}
