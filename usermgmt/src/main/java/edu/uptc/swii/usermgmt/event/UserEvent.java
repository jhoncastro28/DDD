package edu.uptc.swii.usermgmt.event;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.uptc.swii.usermgmt.domain.User;

public class UserEvent implements Serializable {

  private String eventId;
  private String eventType;
  private LocalDateTime timestamp;
  private User user;

  public UserEvent() {
  }

  public UserEvent(String eventType, User user) {
    this.eventId = java.util.UUID.randomUUID().toString();
    this.eventType = eventType;
    this.timestamp = LocalDateTime.now();
    this.user = user;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserEvent{" +
        "eventId='" + eventId + '\'' +
        ", eventType='" + eventType + '\'' +
        ", timestamp=" + timestamp +
        ", user=" + user +
        '}';
  }
}
