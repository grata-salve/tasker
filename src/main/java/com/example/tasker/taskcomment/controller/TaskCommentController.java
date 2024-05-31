package com.example.tasker.taskcomment.controller;

import com.example.tasker.taskcomment.model.TaskCommentDto;
import com.example.tasker.taskcomment.service.TaskCommentService;
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
@RequestMapping("/task-comment")
@RequiredArgsConstructor
public class TaskCommentController {

  private final TaskCommentService taskCommentService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskCommentDto createTaskComment(@RequestBody TaskCommentDto taskCommentDto) {
    return taskCommentService.createTaskComment(taskCommentDto);
  }

  @GetMapping("/get/{taskCommentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto getTaskComment(@PathVariable Long taskCommentId) {
    return taskCommentService.getTaskCommentById(taskCommentId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto updateTask(@RequestBody TaskCommentDto taskCommentDto) {
    return taskCommentService.updateTaskComment(taskCommentDto);
  }

  @DeleteMapping("/delete/{taskCommentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto deleteTaskComment(@PathVariable Long taskCommentId) {
    return taskCommentService.deleteTaskComment(taskCommentId);
  }
}
