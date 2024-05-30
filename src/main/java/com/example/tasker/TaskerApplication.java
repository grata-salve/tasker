package com.example.tasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskerApplication.class, args);
  }

}
