package com.ghostcat.dockerdemo.rabbitmq.service;

import com.ghostcat.dockerdemo.rabbitmq.config.RabbitConstants;
import com.ghostcat.dockerdemo.rabbitmq.vo.RabbitMsgVO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RabbitListener(queues = RabbitConstants.DEMO_TOPIC_QUEUE)
public class DemoTopicQueueReceiver {

    private String msgTmp = "%s Received msg from DemoTopicQueue : %s";

    @RabbitHandler
    public void handleDemoTopicQueue(RabbitMsgVO rabbitMsgVO) {

        LocalDateTime now = LocalDateTime.now();

        System.out.println(String.format(msgTmp, now, rabbitMsgVO));
    }
}
