package edu.uptc.swii.usermgmt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.usermgmt.config.RabbitMQConfig;
import edu.uptc.swii.usermgmt.event.UserEvent;

@Service
public class UserEventConsumer {

  private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);

  @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
  public void handleUserCreatedEvent(UserEvent event) {
    log.info("📨 Received USER_CREATED event: {}", event);
    log.info("✅ User {} {} was created successfully", event.getUser().getFirstName(),
        event.getUser().getLastName());
  }

  @RabbitListener(queues = RabbitMQConfig.USER_DELETED_QUEUE)
  public void handleUserDeletedEvent(UserEvent event) {
    log.info("📨 Received USER_DELETED event: {}", event);
    log.info("🗑️ User with ID {} was deleted", event.getUser().getUserId());
  }
}
