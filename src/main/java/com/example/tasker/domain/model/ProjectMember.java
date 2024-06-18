package com.example.tasker.domain.model;

import com.example.tasker.domain.constants.ProjectRole;
import com.example.tasker.domain.model.base.AbstractIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "project_members")
public class ProjectMember extends AbstractIdentifiable {

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private User member;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ProjectRole role;
}