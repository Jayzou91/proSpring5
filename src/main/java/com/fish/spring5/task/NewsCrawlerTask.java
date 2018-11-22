package com.fish.spring5.task;

import com.fish.spring5.crawler.NewsController;
import com.fish.spring5.crawler.NewsCrawlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
@Component
public class NewsCrawlerTask {

    private final Logger logger = LoggerFactory.getLogger(NewsCrawlerTask.class);

    @Autowired
    private NewsController newsController;


    @Scheduled(initialDelay = 1000, fixedRate = 300000)
    public void execute() {
        try {
            NewsCrawlerFactory factory = new NewsCrawlerFactory();
            newsController.doCrawl(factory, 7);
        } catch (Exception e) {
            logger.error("NewsCrawlerTask.execute error.", e);
        }
    }

}
