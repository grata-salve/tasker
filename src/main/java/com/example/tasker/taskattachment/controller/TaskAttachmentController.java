package com.example.tasker.taskattachment.controller;

import com.example.tasker.taskattachment.model.TaskAttachmentDto;
import com.example.tasker.taskattachment.model.TaskAttachmentFileNameDto;
import com.example.tasker.taskattachment.service.TaskAttachmentService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/task-attachment")
@RequiredArgsConstructor
public class TaskAttachmentController {

  private final TaskAttachmentService taskAttachmentService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskAttachmentFileNameDto createTaskAttachment(
      @RequestParam("fileData") MultipartFile fileData,
      @RequestParam("taskId") Long taskId,
      @RequestParam("memberId") Long memberId)
      throws IOException {
    return taskAttachmentService.createTaskAttachment(fileData, taskId, memberId);
  }

  @GetMapping("/get/{taskAttachmentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskAttachmentDto getTaskAttachment(@PathVariable Long taskAttachmentId) {
    return taskAttachmentService.getTaskAttachmentById(taskAttachmentId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public TaskAttachmentFileNameDto updateTaskAttachment(
      @RequestParam("id") Long id,
      @RequestParam("fileData") MultipartFile fileData,
      @RequestParam("taskId") Long taskId,
      @RequestParam("memberId") Long memberId)
      throws IOException {
    return taskAttachmentService.updateTaskAttachment(id, fileData, taskId, memberId);
  }

  @DeleteMapping("/delete/{taskAttachmentId}")
  @ResponseStatus(HttpStatus.OK)
  public TaskAttachmentDto deleteTaskAttachment(@PathVariable Long taskAttachmentId) {
    return taskAttachmentService.deleteTaskAttachment(taskAttachmentId);
  }
}
