package com.news.system.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PositiveWords {

    public static final Set<String> WORDS =
            new HashSet<>(Arrays.asList(
                    "up", "rise", "good", "success", "high"
            ));

    private PositiveWords() {
    }

}