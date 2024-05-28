package com.example.tasker.domain.model;

import com.example.tasker.domain.model.base.AbstractCreationTimeIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task_attachments")
public class TaskAttachment extends AbstractCreationTimeIdentifiable {

  @ManyToOne
  @JoinColumn(name = "task_id", nullable = false)
  private Task task;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private User member;

  @NotBlank
  private byte[] fileData;
}
