package com.news.system.mocknews;

import com.news.system.generator.NewsGenerator;
import com.news.system.model.NewsItem;
import com.news.system.service.KafkaProducerService;
import org.apache.kafka.common.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MockNewsFeed {

    private final long frequencyMillis;
    private final KafkaProducerService kafkaProducerService;
    private final NewsGenerator newsGenerator = new NewsGenerator();
    private static final Logger log = LoggerFactory.getLogger(MockNewsFeed.class);

    public MockNewsFeed(long frequencyMillis, KafkaProducerService kafkaProducerService) {
        this.frequencyMillis = frequencyMillis;
        this.kafkaProducerService = kafkaProducerService;
    }

    public void start() {

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {

            try {

                NewsItem item = newsGenerator.generate();
                kafkaProducerService.send(format(item));

            } catch (KafkaException e) {
                log.error("Kafka error", e);
            } catch (Exception e) {
                log.error("feed error", e);
            }

        }, 0, frequencyMillis, TimeUnit.MILLISECONDS);
    }

    private String format(NewsItem item) {
        return item.getHeadline() + "|" + item.getPriority();
    }

}