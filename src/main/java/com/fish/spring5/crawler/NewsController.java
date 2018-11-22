package com.fish.spring5.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/22
 */
@Component("newsController")
public class NewsController {

    public void doCrawl(CrawlController.WebCrawlerFactory factory, int numberOfCrawlers) throws Exception {

        String crawlStorageFolder = "D:/Temp/crawl";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("http://news.ifeng.com");

        controller.startNonBlocking(factory, numberOfCrawlers);
    }
}
