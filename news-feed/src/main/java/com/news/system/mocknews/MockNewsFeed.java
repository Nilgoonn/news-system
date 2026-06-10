package com.news.system.mocknews;

import com.news.system.model.NewsItem;
import com.news.system.generator.NewsGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MockNewsFeed {

    private final long frequencyMillis;
    private final NewsGenerator newsGenerator = new NewsGenerator();
    private static final Logger log = LoggerFactory.getLogger(MockNewsFeed.class);

    public MockNewsFeed(long frequencyMillis) {
        this.frequencyMillis = frequencyMillis;
    }

    public void start(String host, int port) throws Exception {

        Socket socket = new Socket(host, port);
        PrintWriter writer =
                new PrintWriter(socket.getOutputStream(), true);

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {

            try {
                NewsItem item = newsGenerator.generate();
                writer.println(format(item));

            } catch (Exception e) {
                log.error("Feed error", e);
            }

        }, 0, frequencyMillis, TimeUnit.MILLISECONDS);
    }

    private String format(NewsItem item) {
        return item.getHeadline() + "|" + item.getPriority();
    }

}