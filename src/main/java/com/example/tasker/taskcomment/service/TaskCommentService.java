package com.example.tasker.taskcomment.service;

import com.example.tasker.domain.model.TaskComment;
import com.example.tasker.domain.model.mapper.TaskCommentMapper;
import com.example.tasker.domain.repository.TaskCommentRepository;
import com.example.tasker.taskcomment.model.TaskCommentDto;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskCommentService {

  private final TaskCommentRepository taskCommentRepository;
  private final TaskCommentMapper taskCommentMapper;

  @Transactional
  public TaskCommentDto createTaskComment(TaskCommentDto taskCommentDto) {
    TaskComment taskComment = taskCommentRepository.save(taskCommentMapper.toEntity(taskCommentDto));
    return taskCommentMapper.toDto(taskComment);
  }

  @Transactional(readOnly = true)
  public TaskCommentDto getTaskCommentById(Long taskCommentId) {
    TaskComment taskComment = taskCommentRepository.findById(taskCommentId).orElseThrow(NoSuchElementException::new);
    return taskCommentMapper.toDto(taskComment);
  }

  @Transactional
  public TaskCommentDto updateTaskComment(TaskCommentDto taskCommentDto) {
    TaskComment taskComment = taskCommentMapper.toEntity(taskCommentDto);
    return taskCommentMapper.toDto(taskCommentRepository.save(taskComment));
  }

  @Transactional
  public TaskCommentDto deleteTaskComment(Long taskCommentId) {
    TaskComment taskComment = taskCommentRepository.findById(taskCommentId).orElseThrow(NoSuchElementException::new);
    taskCommentRepository.deleteById(taskCommentId);
    return taskCommentMapper.toDto(taskComment);
  }
}
