package com.ljj.front;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test01(){
        redisTemplate.opsForValue().set("name","jie");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

}
