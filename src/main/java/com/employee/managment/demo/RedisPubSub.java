package com.employee.managment.demo;

import redis.clients.jedis.JedisPubSub;

public class RedisPubSub extends JedisPubSub {

    @Override
    public void onPMessage(String pattern, String channel, String message) {

        if(channel === "__keyevent@0__:set")
        System.out.println("Pattern: " + pattern + ", Channel: " + channel + ", Message: " + message);
    }
}
