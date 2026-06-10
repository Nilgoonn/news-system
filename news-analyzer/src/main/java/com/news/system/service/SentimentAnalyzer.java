package com.news.system.service;

import com.news.system.util.PositiveWords;

public class SentimentAnalyzer {

    public boolean isPositive(String headline) {

        String[] words = headline.trim().split("\\s+");
        int positiveCount = 0;

        for (String w : words) {
            if (PositiveWords.WORDS.contains(w)) {
                positiveCount++;
            }
        }

        return positiveCount > (words.length / 2.0);
    }

}