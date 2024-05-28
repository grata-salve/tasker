package com.example.tasker.taskhistory.service;

import com.example.tasker.domain.model.TaskHistory;
import com.example.tasker.domain.model.mapper.TaskHistoryMapper;
import com.example.tasker.domain.repository.TaskHistoryRepository;
import com.example.tasker.taskhistory.model.TaskHistoryDto;
import java.util.NoSuchElementException;
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

  @Transactional(readOnly = true)
  public TaskHistoryDto getTaskHistoryById(Long taskHistoryId) {
    TaskHistory taskHistory = taskHistoryRepository.findById(taskHistoryId).orElseThrow(NoSuchElementException::new);
    return taskHistoryMapper.toDto(taskHistory);
  }

  @Transactional
  public TaskHistoryDto updateTaskHistory(TaskHistoryDto taskHistoryDto) {
    TaskHistory taskHistory = taskHistoryMapper.toEntity(taskHistoryDto);
    return taskHistoryMapper.toDto(taskHistoryRepository.save(taskHistory));
  }

  @Transactional
  public TaskHistoryDto deleteTaskHistory(Long taskHistoryId) {
    TaskHistory taskHistory = taskHistoryRepository.findById(taskHistoryId).orElseThrow(NoSuchElementException::new);
    taskHistoryRepository.deleteById(taskHistoryId);
    return taskHistoryMapper.toDto(taskHistory);
  }
}
