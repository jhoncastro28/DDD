package edu.uptc.swii.usermgmt.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String USER_EXCHANGE = "user.exchange";
  public static final String USER_CREATED_QUEUE = "user.created.queue";
  public static final String USER_DELETED_QUEUE = "user.deleted.queue";
  public static final String USER_CREATED_ROUTING_KEY = "user.created";
  public static final String USER_DELETED_ROUTING_KEY = "user.deleted";

  @Bean
  public TopicExchange userExchange() {
    return new TopicExchange(USER_EXCHANGE);
  }

  @Bean
  public Queue userCreatedQueue() {
    return new Queue(USER_CREATED_QUEUE, true);
  }

  @Bean
  public Queue userDeletedQueue() {
    return new Queue(USER_DELETED_QUEUE, true);
  }

  @Bean
  public Binding userCreatedBinding(Queue userCreatedQueue, TopicExchange userExchange) {
    return BindingBuilder.bind(userCreatedQueue)
        .to(userExchange)
        .with(USER_CREATED_ROUTING_KEY);
  }

  @Bean
  public Binding userDeletedBinding(Queue userDeletedQueue, TopicExchange userExchange) {
    return BindingBuilder.bind(userDeletedQueue)
        .to(userExchange)
        .with(USER_DELETED_ROUTING_KEY);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }
}
