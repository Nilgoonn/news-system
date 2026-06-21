package com.news.system.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

import static com.news.system.config.ConfigLoader.properties;

public class KafkaProducerService {

    private static final Logger log =
            LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    public void send(String message) {
        try {
            producer.send(new ProducerRecord<>("news-topic", message),
                    ((recordMetadata, exception) -> {
                        if (exception != null) {
                            log.error("Kafka send failed", exception);
                        }
                    })).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted", e);
        } catch (ExecutionException e) {
            log.error("Cannot send message", e);
        }
    }
}
