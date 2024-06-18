package com.example.tasker.taskattachment.model;

import com.example.tasker.domain.model.TaskAttachment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO for the {@link TaskAttachment} entity
 */
@Data
public class TaskAttachmentDto {

  private Long id;
  private LocalDateTime createdAt;
  private Long taskId;
  private Long memberId;
  @NotNull
  private byte[] fileData;
}