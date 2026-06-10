package com.news.system;

import com.news.system.config.ConfigLoader;
import com.news.system.report.NewsReporter;
import com.news.system.server.NewsServer;
import com.news.system.service.NewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnalyzerApplication {

    private static final Logger log =
            LoggerFactory.getLogger(AnalyzerApplication.class);

    public static void main(String[] args) {

        NewsProcessor processor = new NewsProcessor();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> new NewsServer(ConfigLoader.getPort(), processor).start());
        log.info("Starting News Analyzer on port {}", ConfigLoader.getPort());
        NewsReporter reporter = new NewsReporter(processor);
        reporter.start();
    }
}