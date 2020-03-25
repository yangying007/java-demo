package com.wangzaiplus.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangying
 * @Description:
 * @Date 2020/3/19
 */
@Configuration
public class TopicRabbitmqConfig {

    //绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";
    //主题交换机
    public final static String TOPICEXCHANGE = "topicExchange";

    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitmqConfig.man);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitmqConfig.woman);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TopicRabbitmqConfig.TOPICEXCHANGE);
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }


}
