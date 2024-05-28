package com.example.tasker.task.model;

import com.example.tasker.domain.constants.Priority;
import com.example.tasker.domain.constants.Status;
import com.example.tasker.domain.model.Task;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link Task} entity
 */
@Data
public class TaskDto {

  private Long id;
  private LocalDateTime createdAt;
  private Long projectId;
  @NotBlank
  private String taskName;
  private String description;
  private Status status;
  private LocalDateTime deadline;
  private Priority priority;
  private Long assigned_toId;
}