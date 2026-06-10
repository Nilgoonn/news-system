package com.news.system.generator;

import com.news.system.model.NewsItem;
import com.news.system.util.NewsWords;

import java.util.Random;

public class NewsGenerator {

    private final Random random = new Random();
    private final PriorityGenerator priorityGenerator = new PriorityGenerator();

    public NewsItem generate() {

        String headline = generateHeadline();
        int priority = priorityGenerator.generate();

        return new NewsItem(headline, priority);
    }

    private String generateHeadline() {

        int wordCount = 3 + random.nextInt(3);
        String[] selected = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            selected[i] = NewsWords.WORDS[random.nextInt(NewsWords.WORDS.length)];
        }

        return String.join(" ", selected);
    }

}