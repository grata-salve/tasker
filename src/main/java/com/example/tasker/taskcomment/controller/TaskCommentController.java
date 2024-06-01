package com.example.tasker.taskcomment.controller;

import com.example.tasker.taskcomment.model.TaskCommentDto;
import com.example.tasker.taskcomment.service.TaskCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
  public TaskCommentDto createTaskComment(
      @RequestBody TaskCommentDto taskCommentDto,
      Authentication authentication) {
    return taskCommentService.createTaskComment(taskCommentDto, authentication);
  }

  @GetMapping("/get/{taskCommentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto getTaskComment(@PathVariable Long taskCommentId) {
    return taskCommentService.getTaskCommentById(taskCommentId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto updateTask(
      @RequestBody TaskCommentDto taskCommentDto,
      Authentication authentication) {
    return taskCommentService.updateTaskComment(taskCommentDto, authentication);
  }

  @DeleteMapping("/delete/{taskCommentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskCommentDto deleteTaskComment(
      @PathVariable Long taskCommentId,
      Authentication authentication) {
    return taskCommentService.deleteTaskComment(taskCommentId, authentication);
  }
}
