package com.example.tasker.taskhistory.controller;

import com.example.tasker.taskhistory.model.TaskHistoryDto;
import com.example.tasker.taskhistory.model.TaskHistoryWithComplexityDto;
import com.example.tasker.taskhistory.model.TimePeriodRequest;
import com.example.tasker.taskhistory.service.TaskHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @DeleteMapping("/delete/{taskHistoryId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskHistoryDto deleteTaskHistory(@PathVariable Long taskHistoryId) {
    return taskHistoryService.deleteTaskHistory(taskHistoryId);
  }

  @GetMapping("/getAllByTaskId/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTaskId(@PathVariable Long taskId) {
    return taskHistoryService.getAllTaskHistoryDtosByTaskId(taskId);
  }

  @GetMapping("/getAllByMemberId/{memberId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByMemberId(@PathVariable Long memberId) {
    return taskHistoryService.getAllTaskHistoryDtosByMemberId(memberId);
  }

  @GetMapping("/getAllByMemberIdAndTaskId/{memberId}/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByMemberIdAndTaskId(
  @PathVariable Long memberId, @PathVariable Long taskId) {
    return taskHistoryService.getAllTaskHistoryDtosByMemberIdAndTaskId(memberId, taskId);
  }

  @GetMapping("/getAllByTimePeriodAndTaskId")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTimePeriodAndTaskId(
      @RequestBody TimePeriodRequest timePeriodRequest) {
    return taskHistoryService.getAllTaskHistoryDtosByTimePeriodAndTaskId(timePeriodRequest);
  }

  @GetMapping("/getAllByTimePeriodAndMemberId")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTimePeriodAndMemberId(
      @RequestBody TimePeriodRequest timePeriodRequest) {
    return taskHistoryService.getAllTaskHistoryDtosByTimePeriodAndMemberId(timePeriodRequest);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/getTaskHistoriesWithComplexityByMemberId/{memberId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryWithComplexityDto> getTaskHistoriesWithComplexityByMemberId(@PathVariable Long memberId) {
    return taskHistoryService.getTaskHistoriesWithComplexityByMemberId(memberId);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/getTaskHistoriesWithComplexityByProjectId/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskHistoryWithComplexityDto> getTaskHistoriesWithComplexityByProjectId(@PathVariable Long projectId) {
    return taskHistoryService.getTaskHistoriesWithComplexityByProjectId(projectId);
  }
}
