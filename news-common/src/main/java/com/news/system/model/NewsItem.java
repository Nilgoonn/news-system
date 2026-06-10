package com.news.system.model;

public class NewsItem {

    private final String headline;
    private final int priority;

    public NewsItem(String headline, int priority) {
        this.headline = headline;
        this.priority = priority;
    }

    public String getHeadline() {
        return headline;
    }

    public int getPriority() {
        return priority;
    }

}