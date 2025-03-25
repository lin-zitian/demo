package com.example.demo.controller;

import com.example.demo.common.BaseResult;
import com.example.demo.mqtt.producer.MqttMessageSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mqtt")
@Api(tags = "测试发送mqtt消息")
public class MqttController {
    @Resource
    private MqttMessageSender mqttMessageSender;

    @PostMapping
    @ApiOperation("发送消息到test主题")
    public BaseResult<String> sendMessage(@RequestParam("message") String message) {
        mqttMessageSender.send("test", 1,message.getBytes());
        return BaseResult.ok();
    }
}
