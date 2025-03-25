package com.example.demo.config;

import com.example.demo.constant.MqttConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * 生产者配置
 *
 * @author jyy
 * @date 2024-11-26
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class MqttOutboundConfiguration {

    @Resource
    private MqttConfiguration mqttConfiguration;

    /**
     * MQTT信息通道（生产者）
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（生产者）
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        // 客户端id
        String clientId = mqttConfiguration.getClientId();
        // 默认主题
        String defaultTopic = mqttConfiguration.getDefaultTopic();
        MqttPahoClientFactory mqttPahoClientFactory = mqttConfiguration.mqttClientFactory();

        // 发送消息和消费消息Channel可以使用相同MqttPahoClientFactory
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + MqttConstant.CLIENT_PREFIX_PRODUCERS, mqttPahoClientFactory);
        // true，异步，发送消息时将不会阻塞。
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        // 默认QoS
        messageHandler.setDefaultQos(1);
        // Paho消息转换器
        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
        // defaultPahoMessageConverter.setPayloadAsBytes(true);
        // 发送默认按字节类型发送消息
        messageHandler.setConverter(defaultPahoMessageConverter);
        return messageHandler;
    }

}
