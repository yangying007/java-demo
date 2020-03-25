package com.wangzaiplus.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangying
 * @Description:
 * @Date 2020/3/6
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    log.info("ConfirmCallback:  消息成功发送到Exchange!");
                } else {
                    log.error("ConfirmCallback:  消息发送到Exchange失败!");
                    log.error("ConfirmCallback:  "+"相关数据："+correlationData);
                    log.error("ConfirmCallback:  "+"原因："+cause);
                }
            }
        });

        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback:     "+"消息："+message);
            log.info("ReturnCallback:     "+"回应码："+replyCode);
            log.info("ReturnCallback:     "+"回应信息："+replyText);
            log.info("ReturnCallback:     "+"交换机："+exchange);
            log.info("ReturnCallback:     "+"路由键："+routingKey);
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
