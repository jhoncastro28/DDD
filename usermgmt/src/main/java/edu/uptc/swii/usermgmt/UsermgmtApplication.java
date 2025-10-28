package edu.uptc.swii.usermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "edu.uptc.swii.usermgmt")
public class UsermgmtApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsermgmtApplication.class, args);
    System.out.println("User Management Service running at: http://localhost:8080");
  }

}
