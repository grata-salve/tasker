package com.example.tasker.taskattachment.service;


import com.example.tasker.domain.model.TaskAttachment;
import com.example.tasker.domain.model.mapper.TaskAttachmentMapper;
import com.example.tasker.domain.repository.TaskAttachmentRepository;
import com.example.tasker.taskattachment.model.TaskAttachmentDto;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskAttachmentService {

  private final TaskAttachmentRepository taskAttachmentRepository;
  private final TaskAttachmentMapper taskAttachmentMapper;

  @Transactional
  public TaskAttachmentDto createTaskAttachment(TaskAttachmentDto taskAttachmentDto) {
    TaskAttachment taskAttachment = taskAttachmentRepository.save(taskAttachmentMapper.toEntity(taskAttachmentDto));
    return taskAttachmentMapper.toDto(taskAttachment);
  }

  @Transactional(readOnly = true)
  public TaskAttachmentDto getTaskAttachmentById(Long taskAttachmentId) {
    TaskAttachment taskAttachment = taskAttachmentRepository.findById(taskAttachmentId).orElseThrow(NoSuchElementException::new);
    return taskAttachmentMapper.toDto(taskAttachment);
  }

  @Transactional
  public TaskAttachmentDto updateTaskAttachment(TaskAttachmentDto taskAttachmentDto) {
    TaskAttachment taskAttachment = taskAttachmentMapper.toEntity(taskAttachmentDto);
    return taskAttachmentMapper.toDto(taskAttachmentRepository.save(taskAttachment));
  }

  @Transactional
  public TaskAttachmentDto deleteTaskAttachment(Long taskAttachmentId) {
    TaskAttachment taskAttachment = taskAttachmentRepository.findById(taskAttachmentId).orElseThrow(NoSuchElementException::new);
    taskAttachmentRepository.deleteById(taskAttachmentId);
    return taskAttachmentMapper.toDto(taskAttachment);
  }
}
