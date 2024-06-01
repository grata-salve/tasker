package com.example.tasker.domain.model;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.base.AbstractCreationTimeIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_history")
public class TaskHistory extends AbstractCreationTimeIdentifiable {

  @ManyToOne
  @JoinColumn(name = "task_id", nullable = false)
  private Task task;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private User member;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Action action;

  private String description;
}
