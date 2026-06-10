package com.news.system.generator;

import java.util.Random;

public class PriorityGenerator {

    private final Random random = new Random();

    private final int[] priority = {
            0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1,
            2, 2, 2, 2,
            3, 3, 3,
            4, 4,
            5,
            6,
            7,
            8,
            9
    };

    public int generate() {
        return priority[random.nextInt(priority.length)];
    }

}