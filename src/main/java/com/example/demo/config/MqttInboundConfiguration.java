package com.example.demo.config;


import com.example.demo.constant.MqttConstant;
import com.example.demo.mqtt.consumer.MqttMessageReceiver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * 消费者配置
 */
@Slf4j
@AllArgsConstructor
@Configuration
@IntegrationComponentScan
public class MqttInboundConfiguration {

    @Resource
    private MqttConfiguration mqttConfiguration;

    @Resource
    private MqttMessageReceiver mqttMessageReceiver;

    /**
     * 此处可以使用其他消息通道
     * MQTT信息通道（消费者）
     * Spring Integration默认的消息通道，它允许将消息发送给一个订阅者，然后阻碍发送直到消息被接收。
     */
    @Bean
    public MessageChannel mqttInBoundChannel() {
        return new DirectChannel();
    }

    /**
     * mqtt入站消息处理工具，对于指定消息入站通道接收到生产者生产的消息后处理消息的工具。
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInBoundChannel")
    public MessageHandler mqttMessageHandler() {
        return this.mqttMessageReceiver;
    }

    /**
     * MQTT消息订阅绑定（消费者）
     * 适配器, 两个topic共用一个adapter
     * 客户端作为消费者，订阅主题，消费消息
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        // 获取客户端id
        String clientId = mqttConfiguration.getClientId();
        // 获取默认主题
//         String defaultTopic = mqttConfiguration.getDefaultTopic();

        // 获取所有配置的主题
        String[] topics = mqttConfiguration.getAllTopics();

        // 获取客户端工厂
        MqttPahoClientFactory mqttPahoClientFactory = mqttConfiguration.mqttClientFactory();

        // Paho客户端消息驱动通道适配器，主要用来订阅主题
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId + MqttConstant.CLIENT_SUFFIX_CONSUMERS,
                mqttPahoClientFactory,
                // TODO 这后面填写需要订阅的主题，这里是一个可变参数，可以填写多个，用逗号隔开。例如，我再订阅一个yy/+的主题
//                defaultTopic,
//                "yy/+",
                
                // TODO  把所有订阅主题都放在一起，方便管理
                topics
        );
        adapter.setCompletionTimeout(5000);
        // Paho消息转换器
        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
        // 按字节接收消息
        // defaultPahoMessageConverter.setPayloadAsBytes(true);
        adapter.setConverter(defaultPahoMessageConverter);
        // 设置QoS
        adapter.setQos(1);
        // 设置订阅通道
        adapter.setOutputChannel(mqttInBoundChannel());
        return adapter;
    }

}

