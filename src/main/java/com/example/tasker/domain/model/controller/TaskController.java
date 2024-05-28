package com.example.tasker.domain.model.controller;

import com.example.tasker.task.model.TaskDto;
import com.example.tasker.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskDto createTask(@RequestBody TaskDto taskDto) {
    return taskService.createTask(taskDto);
  }

  @GetMapping("/get/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskDto getTask(@PathVariable Long taskId) {
    return taskService.getTaskById(taskId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public TaskDto updateTask(@RequestBody TaskDto taskDto) {
    return taskService.updateTask(taskDto);
  }

  @DeleteMapping("/delete/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskDto deleteTask(@PathVariable Long taskId) {
    return taskService.deleteTask(taskId);
  }

}
