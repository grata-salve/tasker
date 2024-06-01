package com.example.tasker.taskcomment.service;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskComment;
import com.example.tasker.domain.model.User;
import com.example.tasker.domain.model.mapper.TaskCommentMapper;
import com.example.tasker.domain.repository.TaskCommentRepository;
import com.example.tasker.taskcomment.model.TaskCommentDto;
import com.example.tasker.taskhistory.service.TaskHistoryService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskCommentService {

  private final TaskCommentRepository taskCommentRepository;
  private final TaskCommentMapper taskCommentMapper;
  private final TaskHistoryService taskHistoryService;

  @Transactional
  public TaskCommentDto createTaskComment(TaskCommentDto taskCommentDto, Authentication authentication) {
    TaskComment taskComment = taskCommentRepository.save(taskCommentMapper.toEntity(taskCommentDto));
    var user = (User) authentication.getPrincipal();
    taskHistoryService.createTaskHistoryAuto(taskComment.getTask().getId(), user.getId(), Action.COMMENTED);
    return taskCommentMapper.toDto(taskComment);
  }

  @Transactional(readOnly = true)
  public TaskCommentDto getTaskCommentById(Long taskCommentId) {
    TaskComment taskComment = taskCommentRepository.findById(taskCommentId).orElseThrow(NoSuchElementException::new);
    return taskCommentMapper.toDto(taskComment);
  }

  @Transactional
  public TaskCommentDto updateTaskComment(TaskCommentDto taskCommentDto, Authentication authentication) {
    TaskComment taskComment = taskCommentMapper.toEntity(taskCommentDto);
    var user = (User) authentication.getPrincipal();
    taskHistoryService.createTaskHistoryAuto(taskComment.getTask().getId(), user.getId(), Action.UPDATED);
    return taskCommentMapper.toDto(taskCommentRepository.save(taskComment));
  }

  @Transactional
  public TaskCommentDto deleteTaskComment(Long taskCommentId, Authentication authentication) {
    TaskComment taskComment = taskCommentRepository.findById(taskCommentId).orElseThrow(NoSuchElementException::new);
    taskCommentRepository.deleteById(taskCommentId);
    var user = (User) authentication.getPrincipal();
    taskHistoryService.createTaskHistoryAuto(taskComment.getTask().getId(), user.getId(), Action.UPDATED);
    return taskCommentMapper.toDto(taskComment);
  }
}
