package com.news.system.service;

import com.news.system.model.NewsItem;
import com.news.system.model.TimedNewsItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class NewsProcessor {

    private final SentimentAnalyzer sentimentAnalyzer =
            new SentimentAnalyzer();

    private final Queue<TimedNewsItem> queue =
            new ConcurrentLinkedQueue<>();

    private static final Logger log =
            LoggerFactory.getLogger(NewsProcessor.class);

    public void process(String message) {

        String[] parts = message.split("\\|");

        if (parts.length != 2) {
            log.warn("Invalid message format: {}", message);
            return;
        }

        String headline = parts[0];
        int priority = Integer.parseInt(parts[1]);

        log.debug("Processing: {} | {}", headline, priority);

        if (sentimentAnalyzer.isPositive(headline)) {
            log.debug("Positive news detected: {}", headline);

            queue.add(new TimedNewsItem(new NewsItem(headline, priority), Instant.now()));
        }
    }

    public List<TimedNewsItem> getLast10Seconds() {

        Instant now = Instant.now();
        Instant tenSecondsAgo = now.minusSeconds(10);
        List<TimedNewsItem> snapshot = new ArrayList<>(queue);

        return snapshot.stream()
                .filter(news -> news.getReceivedAt().isAfter(tenSecondsAgo))
                .collect(Collectors.toList());
    }

}