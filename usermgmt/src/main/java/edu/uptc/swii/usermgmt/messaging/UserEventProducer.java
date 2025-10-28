package edu.uptc.swii.usermgmt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.usermgmt.config.RabbitMQConfig;
import edu.uptc.swii.usermgmt.domain.User;
import edu.uptc.swii.usermgmt.event.UserEvent;

@Service
public class UserEventProducer {

  private static final Logger log = LoggerFactory.getLogger(UserEventProducer.class);

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendUserCreatedEvent(User user) {
    UserEvent event = new UserEvent("USER_CREATED", user);
    log.info("Sending USER_CREATED event: {}", event);
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.USER_EXCHANGE,
        RabbitMQConfig.USER_CREATED_ROUTING_KEY,
        event);
  }

  public void sendUserDeletedEvent(User user) {
    UserEvent event = new UserEvent("USER_DELETED", user);
    log.info("Sending USER_DELETED event: {}", event);
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.USER_EXCHANGE,
        RabbitMQConfig.USER_DELETED_ROUTING_KEY,
        event);
  }
}
