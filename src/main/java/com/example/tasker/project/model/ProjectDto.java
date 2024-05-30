package com.example.tasker.project.model;

import com.example.tasker.domain.model.Project;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link Project} entity
 */
@Data
public class ProjectDto {

  private Long id;
  private LocalDateTime createdAt;
  @NotBlank
  private String projectName;
  private String description;
  private Long projectCreatorId;
}