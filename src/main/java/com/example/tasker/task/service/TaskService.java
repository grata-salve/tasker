package com.example.tasker.task.service;

import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.mapper.TaskMapper;
import com.example.tasker.domain.repository.TaskRepository;
import com.example.tasker.task.model.TaskDto;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Transactional
  public TaskDto createTask(TaskDto taskDto) {
    Task task = taskRepository.save(taskMapper.toEntity(taskDto));
    return taskMapper.toDto(task);
  }

  @Transactional(readOnly = true)
  public TaskDto getTaskById(Long taskId) {
    Task task = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);
    return taskMapper.toDto(task);
  }

  @Transactional
  public TaskDto updateTask(TaskDto taskDto) {
    Task task = taskMapper.toEntity(taskDto);
    return taskMapper.toDto(taskRepository.save(task));
  }

  @Transactional
  public TaskDto deleteTask(Long taskId) {
    Task task = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);
    taskRepository.deleteById(taskId);
    return taskMapper.toDto(task);
  }
}
