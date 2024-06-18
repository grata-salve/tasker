package com.example.tasker.domain.model;


import com.example.tasker.domain.constants.Priority;
import com.example.tasker.domain.constants.Status;
import com.example.tasker.domain.model.base.AbstractCreationTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task extends AbstractCreationTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  protected Long id;

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @NotBlank
  private String taskName;

  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;

  private LocalDateTime deadline;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @Min(value = 1, message = "< 1 is not allowed")
  @Max(value = 10, message = "> 10 is not allowed")
  private int complexity;

  @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
  private List<TaskAssigned> taskAssignedMembers;

  @OneToMany(mappedBy = "task")
  private List<TaskAttachment> taskAttachment;

  @OneToMany(mappedBy = "task")
  private List<TaskHistory> taskHistory;
}