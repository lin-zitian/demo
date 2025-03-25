package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * mqtt相关配置信息
 *
 * @author jyy
 * @date 2024-11-26
 */
@Slf4j
@Setter
@Getter
@Configuration
@ConfigurationProperties("spring.mqtt")
public class MqttConfiguration {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接地址
     */
    private String hostUrl;

    /**
     * 客户Id
     */
    private String clientId;

    /**
     * 默认连接话题
     */
    private String defaultTopic;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 保持连接数
     */
    private int keepalive;

    /**
     * 要订阅的其他主题
     */
    private List<String> topics;

    /**
     * 客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
     */
    private static final byte[] WILL_DATA = "offline".getBytes();


    /**
     * 获取所有订阅的主题
     *
     * @return 所有订阅的主题
     */
    public String[] getAllTopics() {
    	// 校验配置文件是否配置
        if (CollectionUtils.isEmpty(topics)) {
            this.topics = new ArrayList<>();
        }
        // 将默认主题条件到其他主题里
        this.topics.add(defaultTopic);
        // 返回主题数组
        return topics.toArray(new String[0]);
    }

    /**
     * 注册MQTT客户端工厂
     *
     * @return MqttPahoClientFactory
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        // 客户端工厂
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置连接的地址
        options.setServerURIs(new String[]{hostUrl});

        // 如果设置为 false，客户端和服务器将在客户端、服务器和连接重新启动时保持状态。随着状态的保持：
        // 即使客户端、服务器或连接重新启动，消息传递也将可靠地满足指定的 QOS。服务器将订阅视为持久的。
        // 如果设置为 true，客户端和服务器将不会在客户端、服务器或连接重新启动时保持状态。
        options.setCleanSession(true);

        // 设置超时时间，该值以秒为单位，必须>0，定义了客户端等待与 MQTT 服务器建立网络连接的最大时间间隔。
        // 默认超时为 30 秒。值 0 禁用超时处理，这意味着客户端将等待直到网络连接成功或失败。
        options.setConnectionTimeout(10);

        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
        // 此值以秒为单位，定义发送或接收消息之间的最大时间间隔，必须>0
        // 但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);

        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
        options.setWill("willTopic", WILL_DATA, 2, false);

        //自动重新连接
        options.setAutomaticReconnect(true);
        factory.setConnectionOptions(options);

        log.info("初始化 MQTT 配置");

        return factory;
    }
}


