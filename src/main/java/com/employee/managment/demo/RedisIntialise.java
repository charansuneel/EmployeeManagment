package com.employee.managment.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisIntialise {

    @Autowired
    Jedis redisClient;

    @PostConstruct
    public void init() {
       redisClient.setnx("COUNTER_KEY", "5");
    }
}
