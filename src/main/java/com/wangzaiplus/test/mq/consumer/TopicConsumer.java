package com.wangzaiplus.test.mq.consumer;

import com.rabbitmq.client.Channel;
import com.wangzaiplus.test.config.DirectRabbitConfig;
import com.wangzaiplus.test.config.TopicRabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author yangying
 * @Description:
 * @Date 2020/3/19
 */
@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(queues = TopicRabbitmqConfig.man)
    public void consume(Message message, Channel channel,Map testMessage) throws IOException{
        System.out.println("TopicConsumer消费者收到消息  : " + testMessage.toString());
    }
}
