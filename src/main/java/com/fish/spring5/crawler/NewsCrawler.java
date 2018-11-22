package com.fish.spring5.crawler;

import com.fish.spring5.mq.NewsProvider;
import com.fish.spring5.mq.msg.NewsMsg;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/21
 */
@Component
public class NewsCrawler extends WebCrawler {

    private static final String PAGE_SUFFIX = ".shtml";
    private static final String STARTING_PAGE_URL = "http://news.ifeng.com";

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    @Autowired
    private NewsProvider newsProvider;


    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return href.endsWith(PAGE_SUFFIX) && href.startsWith(STARTING_PAGE_URL);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        logger.debug(page.getWebURL().getURL());
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);
            String title = doc.select("#artical > h1").text();
            Elements timeAndSourceElement = doc.select("#artical_sth > p.p_time");
            if (StringUtils.isNotBlank(title) && timeAndSourceElement != null) {
                String time = timeAndSourceElement.select("span.ss01").text();
                String source = timeAndSourceElement.select("span.ss03 > a").text();
                String content = doc.select("#main_content").text();
                NewsMsg msg = new NewsMsg(title, content, source, time);
                newsProvider.sendMessage(msg, 0L);
            }
        }
    }


}
