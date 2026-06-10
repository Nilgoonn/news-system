package com.news.system.server;

import com.news.system.service.NewsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final NewsProcessor processor;
    private static final Logger log =
            LoggerFactory.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket, NewsProcessor processor) {
        this.socket = socket;
        this.processor = processor;
    }

    @Override
    public void run() {
        log.debug("Client connected: {}", socket.getRemoteSocketAddress());

        try (Socket s = socket;
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(s.getInputStream())
                     )) {

            String line;
            while ((line = reader.readLine()) != null) {
                log.debug("Raw message received: {}", line);
                processor.process(line);
            }

        } catch (IOException e) {
            log.info("Client disconnected: {}", socket.getRemoteSocketAddress());
        } catch (Exception e) {
            log.error("Unexpected error in client handler", e);
        }
    }

}