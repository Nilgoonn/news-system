package com.news.system;

import com.news.system.report.NewsReporter;
import com.news.system.service.KafkaConsumerService;
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
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(processor);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(kafkaConsumerService::start);
        log.info("Starting News Analyzer");
        NewsReporter reporter = new NewsReporter(processor);
        reporter.start();
    }
}