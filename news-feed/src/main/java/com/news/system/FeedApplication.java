package com.news.system;

import com.news.system.config.ConfigLoader;
import com.news.system.mocknews.MockNewsFeed;
import com.news.system.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedApplication {

    private static final Logger log =
            LoggerFactory.getLogger(FeedApplication.class);

    public static void main(String[] args) throws Exception {

        KafkaProducerService kafkaProducerService = new KafkaProducerService();
        new MockNewsFeed(ConfigLoader.getFrequency(), kafkaProducerService)
                .start();

        log.info("Starting News Feed");
    }

}