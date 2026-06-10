package com.news.system.report;

import com.news.system.model.TimedNewsItem;
import com.news.system.service.NewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewsReporter {

    private final NewsProcessor processor;
    private static final Logger log =
            LoggerFactory.getLogger(NewsReporter.class);

    public NewsReporter(NewsProcessor processor) {
        this.processor = processor;
    }

    public void start() {

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {

            List<TimedNewsItem> last10News = processor.getLast10Seconds();
            log.info("Positive news count (last 10 seconds): {}", last10News.size());

            PriorityQueue<TimedNewsItem> top3News =
                    new PriorityQueue<>(
                            Comparator.comparingInt(
                                    t -> t.getNewsItem().getPriority()
                            )
                    );

            for (TimedNewsItem item : last10News) {
                if (top3News.size() < 3) {
                    top3News.offer(item);
                } else if (item.getNewsItem().getPriority()
                        > top3News.peek().getNewsItem().getPriority()) {
                    top3News.poll();
                    top3News.offer(item);
                }
            }

            top3News.stream()
                    .sorted(Comparator.comparingInt((TimedNewsItem t) ->
                            t.getNewsItem().getPriority()).reversed())
                    .forEach(item ->
                            log.info("Top headline: {} | priority: {}",
                                    item.getNewsItem().getHeadline(),
                                    item.getNewsItem().getPriority()
                            )
                    );

        }, 10, 10, TimeUnit.SECONDS);
    }

}