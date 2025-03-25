package com.example.demo;

import com.example.demo.mqtt.producer.MqttMessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private MqttMessageSender mqttMessageSender;
    @Test
    void contextLoads() {
        mqttMessageSender.send("test", 1, "hello world".getBytes());
    }

}
