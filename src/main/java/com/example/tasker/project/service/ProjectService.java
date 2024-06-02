package com.example.tasker.project.service;

import com.example.tasker.domain.constants.ProjectRole;
import com.example.tasker.domain.model.Project;
import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.mapper.ProjectMapper;
import com.example.tasker.domain.repository.ProjectRepository;
import com.example.tasker.project.model.ProjectDto;
import com.example.tasker.projectmember.model.ProjectMemberDto;
import com.example.tasker.projectmember.service.ProjectMemberService;
import com.example.tasker.task.model.TaskDto;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final ProjectMemberService projectMemberService;

  @Transactional
  public ProjectDto createProject(Long userId, ProjectDto projectDto) {
    projectDto.setProjectCreatorId(userId);
    Project project = projectRepository.save(projectMapper.toEntity(projectDto));

    ProjectMemberDto projectMemberDto = new ProjectMemberDto();
    projectMemberDto.setProjectId(project.getId());
    projectMemberDto.setMemberId(userId);
    projectMemberDto.setRole(ProjectRole.MANAGER);
    projectMemberService.createProjectMember(projectMemberDto);

    return projectMapper.toDto(project);
  }

  @Transactional
  public ProjectDto getProjectById(Long id) {
    Project project = projectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    return projectMapper.toDto(project);
  }

  @Transactional
  public ProjectDto updateProject(ProjectDto projectDto) {
    Project existingProject = projectRepository.findById(projectDto.getId())
        .orElseThrow(NoSuchElementException::new);

    LocalDateTime createdAt = existingProject.getCreatedAt();

    projectMapper.updateProject(projectDto, existingProject);
    Project updatedProject = projectRepository.save(existingProject);
    ProjectDto updatedProjectDto = projectMapper.toDto(updatedProject);
    updatedProjectDto.setCreatedAt(existingProject.getCreatedAt());

    updatedProjectDto.setCreatedAt(createdAt);

    return updatedProjectDto;
  }

  @Transactional
  public ProjectDto deleteProject(Long id) {
    Project project = projectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    projectRepository.delete(project);
    return projectMapper.toDto(project);
  }
}
