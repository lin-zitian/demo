package com.example.demo.mqtt.producer;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者处理器
 *
 * @author jyy
 * @date 2024-11-26
 */
@Component
public class MqttMessageSender {

    @Resource
    private MqttGateway mqttGateway;

    /**
     * 发送mqtt消息
     *
     * @param topic   主题
     * @param message 内容
     * @return void
     */
    public void send(String topic, String message) {
        mqttGateway.sendToMqtt(topic, message);
    }

    /**
     * 发送包含qos的消息
     *
     * @param topic       主题
     * @param qos         质量
     * @param messageBody 消息体
     * @return void
     */
    public void send(String topic, int qos, JSONObject messageBody) {
        mqttGateway.sendToMqtt(topic, qos, messageBody.toString());
    }

    /**
     * 发送包含qos的消息
     *
     * @param topic   主题
     * @param qos     质量
     * @param message 消息体
     * @return void
     */
    public void send(String topic, int qos, byte[] message) {
        mqttGateway.sendToMqtt(topic, qos, message);
    }
}
