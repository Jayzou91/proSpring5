package com.fish.spring5.dao;

import com.fish.spring5.entity.Singer;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
public interface SingerDao {

    List<Singer> findAll();

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
