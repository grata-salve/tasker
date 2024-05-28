package com.example.tasker.domain.model;

import com.example.tasker.domain.model.base.AbstractCreationTimeIdentifiable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "projects")
public class Project extends AbstractCreationTimeIdentifiable {

  @NotBlank
  private String projectName;

  private String description;

  @ManyToOne
  @JoinColumn(name = "project_creator_id", nullable = false)
  private User projectCreator;

  @OneToMany(mappedBy = "project")
  private List<ProjectMember> projectMembers;
}