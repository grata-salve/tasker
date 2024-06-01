package com.example.tasker.taskattachment.model;

import com.example.tasker.domain.model.TaskAttachment;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A DTO for the {@link TaskAttachment} entity
 */
@Data
@AllArgsConstructor
public class TaskAttachmentFileNameDto {

  private Long id;
  private LocalDateTime createdAt;
  private Long taskId;
  private Long memberId;
  private String fileData;
}