package com.fish.spring5.crawler;

import com.fish.spring5.util.SpringContextUtils;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/22
 */
public class NewsCrawlerFactory implements CrawlController.WebCrawlerFactory {


    @Override
    public WebCrawler newInstance() throws Exception {
        return SpringContextUtils.getBean(NewsCrawler.class);
    }
}
