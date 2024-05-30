package com.example.tasker.taskassigned.controller;

import com.example.tasker.taskassigned.model.TaskAssignedDto;
import com.example.tasker.taskassigned.service.TaskAssignedService;
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
@RequestMapping("/task-assigned")
@RequiredArgsConstructor
public class TaskAssignedController {

  private final TaskAssignedService taskAssignedService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskAssignedDto createTaskAssigned(@RequestBody TaskAssignedDto taskAssignedDto) {
    return taskAssignedService.createTaskAssigned(taskAssignedDto);
  }

  @GetMapping("/get/{taskAssignedId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskAssignedDto getTaskAssigned(@PathVariable Long taskAssignedId) {
    return taskAssignedService.getTaskAssignedById(taskAssignedId);
  }

//  @PutMapping("/update")
//  @ResponseStatus(HttpStatus.OK)
//  public TaskAssignedDto updateTaskAssigned(@RequestBody TaskAssignedDto taskAssignedDto) {
//    return taskAssignedService.updateTaskAssigned(taskAssignedDto);
//  }

  @DeleteMapping("/delete/{taskAssignedId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskAssignedDto deleteTaskAssigned(@PathVariable Long taskAssignedId) {
    return taskAssignedService.deleteTaskAssigned(taskAssignedId);
  }
}
