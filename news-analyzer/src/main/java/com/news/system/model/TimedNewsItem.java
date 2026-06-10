package com.news.system.model;

import java.time.Instant;

public class TimedNewsItem {

    private final NewsItem newsItem;
    private final Instant receivedAt;

    public TimedNewsItem(NewsItem newsItem, Instant receivedAt) {
        this.newsItem = newsItem;
        this.receivedAt = receivedAt;
    }

    public NewsItem getNewsItem() {
        return newsItem;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

}