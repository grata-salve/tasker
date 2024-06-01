package com.example.tasker.taskhistory.model;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskHistory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
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
  @NotNull
  private Action action;
  private String description;
}