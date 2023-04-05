package com.ljj.front.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;
    public static RedisTemplate redis;

    public void getRedisTemplate(){
        redis = this.redisTemplate;
        System.out.println("初始化------------");
    }
}
