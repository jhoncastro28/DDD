package edu.uptc.swii.usermgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.usermgmt.domain.User;
import edu.uptc.swii.usermgmt.repo.mongodb.UserRepository;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }

  @Override
  public User findByUserId(String userId) {
    return userRepository.findByUserId(userId);
  }

  @Override
  public void deleteUser(String userId) {
    userRepository.deleteByUserId(userId);
  }

  @Override
  public List<User> listAllUsers() {
    return userRepository.findAll();
  }
}
