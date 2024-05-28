package com.example.tasker.taskhistory.model;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskHistory;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link TaskHistory} entity
 */
@Data
public class TaskHistoryDto {

  private Long id;
  private LocalDateTime createdAt;
  private Long taskId;
  private Long memberId;
  @NotBlank
  private Action action;
  private String description;
}