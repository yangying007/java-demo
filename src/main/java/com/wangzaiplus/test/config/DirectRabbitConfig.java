package com.wangzaiplus.test.config;

import com.wangzaiplus.test.common.Constant;
import com.wangzaiplus.test.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DirectRabbitConfig {



    @Autowired
    private MsgLogService msgLogService;





    // 登录日志
    public static final String LOGIN_LOG_QUEUE_NAME = "login.log.queue";
    public static final String LOGIN_LOG_EXCHANGE_NAME = "login.log.exchange";
    public static final String LOGIN_LOG_ROUTING_KEY_NAME = "login.log.routing.key";

    @Bean
    public Queue logUserQueue() {
        return new Queue(LOGIN_LOG_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange logUserExchange() {
        return new DirectExchange(LOGIN_LOG_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding logUserBinding() {
        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(LOGIN_LOG_ROUTING_KEY_NAME);
    }

    // 发送邮件
    public static final String MAIL_QUEUE_NAME = "mail.queue";
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key";

    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL_ROUTING_KEY_NAME);
    }

    // 测试直流交换机
    public static final String TEST_DIRECT_QUEUE = "TestDirectQueue";
    public static final String TEST_DIRECT_EXCHANGE = "TestDirectExchange";
    public static final String TEST_DIRECT_ROUTING = "TestDirectRouting";

    @Bean
    public Queue testDirectQueue() {
        return new Queue(TEST_DIRECT_QUEUE, true);
    }

    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange(TEST_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding testDirectBinding() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with(TEST_DIRECT_ROUTING);
    }

}
