package com.example.tasker.taskcomment.model;

import com.example.tasker.domain.model.TaskComment;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link TaskComment} entity
 */
@Data
public class TaskCommentDto {

  private Long id;
  private LocalDateTime createdAt;
  private Long taskId;
  private Long userId;
  @NotBlank
  private String comment;
}