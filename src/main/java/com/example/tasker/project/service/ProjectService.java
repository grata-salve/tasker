package com.example.tasker.project.service;

import com.example.tasker.domain.model.Project;
import com.example.tasker.domain.model.mapper.ProjectMapper;
import com.example.tasker.domain.repository.ProjectRepository;
import com.example.tasker.project.model.ProjectDto;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;

  @Transactional
  public ProjectDto createProject(Long userId, ProjectDto projectDto) {
    projectDto.setUserId(userId);
    Project project = projectRepository.save(projectMapper.toEntity(projectDto));
    return projectMapper.toDto(project);
  }

  @Transactional(readOnly = true)
  public ProjectDto getProjectById(Long id) {
    Project project = projectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    return projectMapper.toDto(project);
  }

  @Transactional
  public ProjectDto updateProject(ProjectDto projectDto) {

    // Получение существующего проекта из базы данных
    Project existingProject = projectRepository.findById(projectDto.getId())
        .orElseThrow(NoSuchElementException::new);

    // Обновление существующего проекта данными из DTO
    projectMapper.updateProject(projectDto, existingProject);

    // Сохранение обновленного проекта
    Project updatedProject = projectRepository.save(existingProject);

    // Возвращение обновленного DTO
    return projectMapper.toDto(updatedProject);

//    Project project = projectMapper.toEntity(projectDto);
//    return projectMapper.toDto(projectRepository.save(project));
  }

  @Transactional
  public ProjectDto deleteProject(Long id) {
    Project project = projectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    projectRepository.delete(project);
    return projectMapper.toDto(project);
  }
}
