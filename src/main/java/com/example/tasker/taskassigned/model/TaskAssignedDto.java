package com.example.tasker.taskassigned.model;

import com.example.tasker.domain.model.ProjectMember;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the {@link ProjectMember} entity
 */
@Data
public class TaskAssignedDto {

  private Long id;
  @NotNull
  private Long taskId;
  @NotNull
  private Long memberId;
}
