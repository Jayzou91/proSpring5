package com.fish.spring5.dao.impl;

import com.fish.spring5.dao.NewsDao;
import com.fish.spring5.entity.News;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements NewsDao {

}
