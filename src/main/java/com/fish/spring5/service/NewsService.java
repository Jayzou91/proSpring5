package com.fish.spring5.service;

import com.fish.spring5.mq.msg.NewsMsg;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
public interface NewsService {

    void addNews(NewsMsg msg);
}
