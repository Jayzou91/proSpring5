package com.fish.spring5.mq.msg;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/22
 */
public class NewsMsg {

    private String title;
    private String content;
    private String source;
    private String publishTime;

    public NewsMsg() {
    }

    public NewsMsg(String title, String content, String source, String oublishTime) {
        this.title = title;
        this.content = content;
        this.source = source;
        this.publishTime = oublishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "NewsMsg{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", publishTime='" + publishTime + '\'' +
                '}';
    }
}
