package com.example.tasker.taskhistory.controller;

import com.example.tasker.taskhistory.model.TaskHistoryDto;
import com.example.tasker.taskhistory.service.TaskHistoryService;
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
@RequestMapping("/task-history")
@RequiredArgsConstructor
public class TaskHistoryController {

  private final TaskHistoryService taskHistoryService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskHistoryDto createHistoryTask(@RequestBody TaskHistoryDto taskHistoryDto) {
    return taskHistoryService.createTaskHistory(taskHistoryDto);
  }

  @GetMapping("/get/{taskHistoryId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskHistoryDto getTaskHistory(@PathVariable Long taskHistoryId) {
    return taskHistoryService.getTaskHistoryById(taskHistoryId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public TaskHistoryDto updateTaskHistory(@RequestBody TaskHistoryDto taskHistoryDto) {
    return taskHistoryService.updateTaskHistory(taskHistoryDto);
  }

  @DeleteMapping("/delete/{taskHistoryId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskHistoryDto deleteTaskHistory(@PathVariable Long taskHistoryId) {
    return taskHistoryService.deleteTaskHistory(taskHistoryId);
  }
}
