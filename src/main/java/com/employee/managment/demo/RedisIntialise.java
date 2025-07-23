package com.employee.managment.demo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@Slf4j
public class RedisIntialise {

    @Autowired
    Jedis redisClient;

    @PostConstruct
    public void init() {
       redisClient.setnx("REGISTER_COUNT", "0");
        new Thread(() -> {
            try {
                RedisPubSub pubSub = new RedisPubSub();
                log.info("Subscribing to Redis key events...");
                Jedis pubSubClient = new Jedis("127.0.0.1", 6379);
                pubSubClient.psubscribe(pubSub, "__keyevent@0__:*");
            } catch (Exception e) {
                log.error("Failed to subscribe to Redis events", e);
            }
        }).start();
    }
}
