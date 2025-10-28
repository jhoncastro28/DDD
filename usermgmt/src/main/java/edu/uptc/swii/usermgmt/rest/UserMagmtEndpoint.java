package edu.uptc.swii.usermgmt.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uptc.swii.usermgmt.domain.User;
import edu.uptc.swii.usermgmt.service.UserMgmtService;

@RestController
@RequestMapping("/users")
@RefreshScope
public class UserMagmtEndpoint {

  private static final Logger log = LoggerFactory.getLogger(UserMagmtEndpoint.class);

  @Autowired
  private UserMgmtService userMgmtService;

  @Value("${message:Welcome to User Management Service}")
  private String message;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> welcome() {
    return ResponseEntity.ok(message);
  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> findUserById(@PathVariable String userId) {
    User user = userMgmtService.findByUserId(userId);
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with userId " + userId);
    }
    return ResponseEntity.ok(user);
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> createUser(@RequestBody User userInput) {
    log.info("Creating user payload: {}", userInput);
    userMgmtService.saveUser(userInput);
    return ResponseEntity.status(HttpStatus.CREATED).body(userInput);
  }

  @GetMapping(value = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<User>> listAllUsers() {
    return ResponseEntity.ok(userMgmtService.listAllUsers());
  }

  @DeleteMapping(value = "/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
    User existingUser = userMgmtService.findByUserId(userId);
    if (existingUser == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with userId " + userId);
    }
    userMgmtService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }
}
