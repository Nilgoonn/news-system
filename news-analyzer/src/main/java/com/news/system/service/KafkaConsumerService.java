package com.news.system.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;

import static com.news.system.config.ConfigLoader.properties;

public class KafkaConsumerService {

    private final NewsProcessor processor;
    private final KafkaConsumer<String, String> consumer;

    public KafkaConsumerService(NewsProcessor processor) {
        this.processor = processor;
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("news-topic"));
    }

    public void start() {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                processor.process(record.value());
            }
        }
    }
}
