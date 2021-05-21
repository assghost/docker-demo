package com.ghostcat.dockerdemo.rabbitmq.config;

import com.ghostcat.dockerdemo.rabbitmq.service.RabbitAckReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //队列 起名：DemoTopicQueue
    @Bean(name = RabbitConstants.DEMO_TOPIC_QUEUE)
    public Queue DemoTopicQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。

        return new Queue(RabbitConstants.DEMO_TOPIC_QUEUE,true);
    }

    //Topic交换机 起名：DemoTopicExchange
    @Bean
    public TopicExchange DemoTopicExchange() {

        return new TopicExchange(RabbitConstants.DEMO_TOPIC_EXCHANGE, true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    public Binding bindingDirect(TopicExchange demoTopicExchange, Queue demoTopicQueue) {
        return BindingBuilder.bind(demoTopicQueue).to(demoTopicExchange).with(RabbitConstants.DEMO_TOPIC_QUEUE);
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallBack());
        rabbitTemplate.setReturnCallback(new RabbitReturnCallBack());

        return rabbitTemplate;
    }


    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                         RabbitAckReceiver rabbitAckReceiver) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        //设置一个队列
        container.setQueueNames(RabbitConstants.DEMO_TOPIC_QUEUE);
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));

        container.setMessageListener(rabbitAckReceiver);

        return container;
    }
}
