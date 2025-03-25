package com.example.demo.mqtt.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 消费者处理器
 *
 * @author jyy
 * @date 2024-11-26
 */
@Slf4j
@Component
public class MqttMessageReceiver implements MessageHandler {

    /**
     * 消息处理
     *
     * @param message 消息
     * @throws MessagingException 消息异常
     */
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            // 获取消息Topic
            MessageHeaders headers = message.getHeaders();
            String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
            log.info("[获取到的消息的topic]：{} ", topic);
            
            // 获取消息体
            String payload = (String) message.getPayload();
            log.info("[获取到的消息的payload]：{} ", payload);

            // 根据主题分别进行消息处理
            if (topic.contains("jyy/")) {
                log.info("接收到jyy/的消息啦，快去处理");
                // TODO 数据处理 payload
        
            }
			// ......
            // TODO else if（匹配其他题主），为了演示方便，这里用的String的contains()方法匹配主题，还可以用String的matches()方法，匹配正则表达式

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}

