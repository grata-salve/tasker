package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.Project;
import com.example.tasker.project.model.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

  Project toEntity(ProjectDto projectDto);

  ProjectDto toDto(Project project);

  void updateProject(ProjectDto projectDto, @MappingTarget Project project);
}
