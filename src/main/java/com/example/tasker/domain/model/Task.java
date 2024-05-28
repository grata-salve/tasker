package com.example.tasker.domain.model;


import com.example.tasker.domain.constants.Priority;
import com.example.tasker.domain.constants.Status;
import com.example.tasker.domain.model.base.AbstractCreationTimeIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task extends AbstractCreationTimeIdentifiable {

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @NotBlank
  private String taskName;

  private String description;

  private Status status;

  private LocalDateTime deadline;

  private Priority priority;

  @ManyToOne
  @JoinColumn(name = "assigned_to_id")
  private User assigned_to;

  @OneToMany(mappedBy = "task")
  private List<TaskAttachment> taskAttachment;

  @OneToMany(mappedBy = "task")
  private List<TaskHistory> taskHistory;
}