package com.example.tasker.taskattachment.service;


import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskAttachment;
import com.example.tasker.domain.model.mapper.TaskAttachmentMapper;
import com.example.tasker.domain.repository.TaskAttachmentRepository;
import com.example.tasker.security.utils.SecurityUtils;
import com.example.tasker.taskattachment.model.TaskAttachmentDto;
import com.example.tasker.taskattachment.model.TaskAttachmentFileNameDto;
import com.example.tasker.taskhistory.service.TaskHistoryService;
import java.io.IOException;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TaskAttachmentService {

  private final TaskAttachmentRepository taskAttachmentRepository;
  private final TaskAttachmentMapper taskAttachmentMapper;
  private final TaskHistoryService taskHistoryService;

  @Transactional
  public TaskAttachmentFileNameDto createTaskAttachment(
      MultipartFile fileData, Long taskId, Long memberId) throws IOException {
    var taskAttachmentDto = new TaskAttachmentDto();
    taskAttachmentDto.setFileData(fileData.getBytes());
    taskAttachmentDto.setTaskId(taskId);
    taskAttachmentDto.setMemberId(memberId);

    TaskAttachment taskAttachment =
        taskAttachmentRepository.save(taskAttachmentMapper.toEntity(taskAttachmentDto));

    taskHistoryService.createTaskHistoryAuto(taskId, SecurityUtils.getCurrentUser().getId(), Action.ATTACHED);

    return new TaskAttachmentFileNameDto(
        taskAttachment.getId(), taskAttachment.getCreatedAt(), taskAttachment.getTask().getId(),
        taskAttachment.getMember().getId(), fileData.getOriginalFilename()
    );
  }

  @Transactional(readOnly = true)
  public TaskAttachmentDto getTaskAttachmentById(Long taskAttachmentId) {
    TaskAttachment taskAttachment = taskAttachmentRepository.findById(taskAttachmentId).orElseThrow(NoSuchElementException::new);
    return taskAttachmentMapper.toDto(taskAttachment);
  }

  @Transactional
  public TaskAttachmentFileNameDto updateTaskAttachment(
      Long id, MultipartFile fileData, Long taskId, Long memberId) throws IOException {
    var taskAttachmentDto = new TaskAttachmentDto();
    taskAttachmentDto.setId(id);
    taskAttachmentDto.setFileData(fileData.getBytes());
    taskAttachmentDto.setTaskId(taskId);
    taskAttachmentDto.setMemberId(memberId);

    TaskAttachment taskAttachment = taskAttachmentMapper.toEntity(taskAttachmentDto);
    var taskAttachmentSaved = taskAttachmentRepository.save(taskAttachment);

    taskHistoryService.createTaskHistoryAuto(taskId, SecurityUtils.getCurrentUser().getId(), Action.UPDATED);

    return new TaskAttachmentFileNameDto(
        taskAttachmentSaved.getId(), taskAttachmentSaved.getCreatedAt(),
        taskAttachmentSaved.getTask().getId(),
        taskAttachmentSaved.getMember().getId(), fileData.getOriginalFilename()
    );
  }

  @Transactional
  public TaskAttachmentDto deleteTaskAttachment(Long taskAttachmentId) {
    TaskAttachment taskAttachment = taskAttachmentRepository.findById(taskAttachmentId).orElseThrow(NoSuchElementException::new);
    taskAttachmentRepository.deleteById(taskAttachmentId);
    taskHistoryService.createTaskHistoryAuto(
        taskAttachment.getTask().getId(), SecurityUtils.getCurrentUser().getId(), Action.DETACHED);
    return taskAttachmentMapper.toDto(taskAttachment);
  }
}