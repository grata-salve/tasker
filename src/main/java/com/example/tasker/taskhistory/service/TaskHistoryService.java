package com.example.tasker.taskhistory.service;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskHistory;
import com.example.tasker.domain.model.mapper.TaskHistoryMapper;
import com.example.tasker.domain.repository.TaskHistoryRepository;
import com.example.tasker.taskhistory.model.TaskHistoryDto;
import com.example.tasker.taskhistory.model.TaskHistoryWithComplexityDto;
import com.example.tasker.taskhistory.model.TimePeriodRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskHistoryService {

  private final TaskHistoryRepository taskHistoryRepository;
  private final TaskHistoryMapper taskHistoryMapper;

  @Transactional
  public TaskHistoryDto createTaskHistory(TaskHistoryDto taskHistoryDto) {
    TaskHistory taskHistory = taskHistoryRepository.save(taskHistoryMapper.toEntity(taskHistoryDto));
    return taskHistoryMapper.toDto(taskHistory);
  }

  @Transactional
  public void createTaskHistoryAuto(Long taskId, Long memberId, Action action) {
    var taskHistoryDto = new TaskHistoryDto();
    taskHistoryDto.setTaskId(taskId);
    taskHistoryDto.setMemberId(memberId);
    taskHistoryDto.setAction(action);
    taskHistoryRepository.save(
        taskHistoryMapper.toEntity(taskHistoryDto));
  }

  @Transactional(readOnly = true)
  public TaskHistoryDto getTaskHistoryById(Long taskHistoryId) {
    TaskHistory taskHistory = taskHistoryRepository.findById(taskHistoryId).orElseThrow(NoSuchElementException::new);
    return taskHistoryMapper.toDto(taskHistory);
  }

  @Transactional
  public TaskHistoryDto deleteTaskHistory(Long taskHistoryId) {
    TaskHistory taskHistory = taskHistoryRepository.findById(taskHistoryId).orElseThrow(NoSuchElementException::new);
    taskHistoryRepository.deleteById(taskHistoryId);
    return taskHistoryMapper.toDto(taskHistory);
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTaskId(Long taskId) {
    List<TaskHistory> taskHistoryList = taskHistoryRepository.findAllByTaskId(taskId);
    return taskHistoryList.stream().map(taskHistoryMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByMemberId(Long memberId) {
    List<TaskHistory> taskHistoryList = taskHistoryRepository.findAllByMemberId(memberId);
    return taskHistoryList.stream().map(taskHistoryMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByMemberIdAndTaskId(Long memberId, Long taskId) {
    List<TaskHistory> taskHistoryList = taskHistoryRepository.findAllByMemberIdAndTaskId(memberId, taskId);
    return taskHistoryList.stream().map(taskHistoryMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTimePeriodAndTaskId(TimePeriodRequest timePeriodRequest) {
    List<TaskHistory> taskHistoryList = taskHistoryRepository.findAllByCreatedAtBetweenAndTaskId(
        timePeriodRequest.getStartDate(), timePeriodRequest.getEndDate(), timePeriodRequest.getTaskId());
    return taskHistoryList.stream().map(taskHistoryMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryDto> getAllTaskHistoryDtosByTimePeriodAndMemberId(TimePeriodRequest timePeriodRequest) {
    List<TaskHistory> taskHistoryList = taskHistoryRepository.findAllByCreatedAtBetweenAndMemberId(
        timePeriodRequest.getStartDate(), timePeriodRequest.getEndDate(), timePeriodRequest.getMemberId());
    return taskHistoryList.stream().map(taskHistoryMapper::toDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryWithComplexityDto> getTaskHistoriesWithComplexityByMemberId(Long memberId) {
    return taskHistoryRepository.findByMemberIdWithTaskComplexity(memberId);
  }

  @Transactional(readOnly = true)
  public List<TaskHistoryWithComplexityDto> getTaskHistoriesWithComplexityByProjectId(Long projectId) {
    return taskHistoryRepository.findByProjectIdWithTaskComplexity(projectId);
  }
}
