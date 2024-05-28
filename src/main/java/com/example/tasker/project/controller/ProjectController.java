package com.example.tasker.project.controller;

import com.example.tasker.domain.model.User;
import com.example.tasker.project.model.ProjectDto;
import com.example.tasker.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectService projectService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ProjectDto createProject(@RequestBody ProjectDto projectDto, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return projectService.createProject(user.getId(), projectDto);
  }

  @GetMapping("/get/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectDto getProject(@PathVariable Long id) {
    return projectService.getProjectById(id);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public ProjectDto updateProject(@RequestBody ProjectDto projectDto) {
    return projectService.updateProject(projectDto);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectDto deleteProject(@PathVariable Long id) {
    return projectService.deleteProject(id);
  }
}
