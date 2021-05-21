package com.ghostcat.dockerdemo.rabbitmq.controller;

import com.ghostcat.dockerdemo.rabbitmq.config.RabbitConstants;
import com.ghostcat.dockerdemo.rabbitmq.vo.RabbitMsgVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/rabbitmq")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public String sendMsgToDemoTopic(String msg) {

        String msgId = UUID.randomUUID().toString();
        LocalDateTime sendTime = LocalDateTime.now();

//        Map<String, Object> msgMap = new HashMap<>();
//        msgMap.put("msg", msg);
//        msgMap.put("msgId", msgId);
//        msgMap.put("sendTime", sendTime);

        RabbitMsgVO rabbitMsgVO = RabbitMsgVO.builder()
                .msg(msg)
                .sendTime(sendTime)
                .msgId(msgId)
                .build();

        //将消息携带绑定键值：DemoTopicQueue 发送到交换机 DemoTopicExchange
        rabbitTemplate.convertAndSend(RabbitConstants.DEMO_TOPIC_QUEUE, rabbitMsgVO);

        return "ok";
    }
}
