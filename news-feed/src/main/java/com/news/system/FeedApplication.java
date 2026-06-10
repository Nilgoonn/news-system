package com.news.system;

import com.news.system.config.ConfigLoader;
import com.news.system.mocknews.MockNewsFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedApplication {

    private static final Logger log =
            LoggerFactory.getLogger(FeedApplication.class);

    public static void main(String[] args) throws Exception {

        new MockNewsFeed(ConfigLoader.getFrequency())
                .start(
                        ConfigLoader.getHost(),
                        ConfigLoader.getPort()
                );

        log.info("Starting News Feed on port {}", ConfigLoader.getPort());
    }

}