package com.news.system.server;

import com.news.system.service.NewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsServer {

    private final int port;
    private final NewsProcessor processor;
    private static final Logger log =
            LoggerFactory.getLogger(NewsServer.class);

    private final ExecutorService pool =
            Executors.newFixedThreadPool(10);

    public NewsServer(int port, NewsProcessor processor) {
        this.port = port;
        this.processor = processor;
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket client = serverSocket.accept();
                pool.submit(new ClientHandler(client, processor));
            }

        } catch (Exception e) {
            log.error("Error starting server", e);
        }
    }

}