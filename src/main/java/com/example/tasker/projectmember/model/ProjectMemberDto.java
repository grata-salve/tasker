package com.example.tasker.projectmember.model;

import com.example.tasker.domain.constants.ProjectRole;
import com.example.tasker.domain.model.ProjectMember;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the {@link ProjectMember} entity
 */
@Data
public class ProjectMemberDto {

  private Long id;
  @NotNull
  private Long projectId;
  @NotNull
  private Long memberId;
  @NotBlank
  private ProjectRole role;
}