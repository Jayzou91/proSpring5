package com.fish.spring5.service.impl;

import com.fish.spring5.dao.NewsDao;
import com.fish.spring5.entity.News;
import com.fish.spring5.mq.msg.NewsMsg;
import com.fish.spring5.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional
    public void addNews(NewsMsg msg) {
        News news = new News();
        news.setTitle(msg.getTitle());
        news.setContent(msg.getContent());
        news.setSource(msg.getSource());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        news.setPublishTime(LocalDateTime.parse(msg.getPublishTime(), formatter));
        news.setCreateTime(LocalDateTime.now());
        news.setDisabled(false);
        newsDao.saveOrUpdate(news);
    }
}
