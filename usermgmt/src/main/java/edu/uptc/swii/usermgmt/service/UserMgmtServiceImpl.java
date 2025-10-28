package edu.uptc.swii.usermgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.usermgmt.domain.User;
import edu.uptc.swii.usermgmt.messaging.UserEventProducer;
import edu.uptc.swii.usermgmt.repo.mongodb.UserRepository;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserEventProducer userEventProducer;

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
    userEventProducer.sendUserCreatedEvent(user);
  }

  @Override
  public User findByUserId(String userId) {
    return userRepository.findByUserId(userId);
  }

  @Override
  public void deleteUser(String userId) {
    User user = userRepository.findByUserId(userId);
    userRepository.deleteByUserId(userId);
    if (user != null) {
      userEventProducer.sendUserDeletedEvent(user);
    }
  }

  @Override
  public List<User> listAllUsers() {
    return userRepository.findAll();
  }
}
