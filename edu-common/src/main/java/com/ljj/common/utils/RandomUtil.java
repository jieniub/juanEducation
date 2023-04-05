package com.ljj.common.utils;

import java.util.Random;

public class RandomUtil {
    public static String getFourBitRandom(){
        Random random = new Random();
        Integer i = 1000 + random.nextInt(9000);
        return i.toString();
    }
}
