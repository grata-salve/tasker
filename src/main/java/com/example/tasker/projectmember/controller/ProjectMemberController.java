package com.example.tasker.projectmember.controller;

import com.example.tasker.projectmember.model.ProjectMemberDto;
import com.example.tasker.projectmember.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/project-member")
@RequiredArgsConstructor
public class ProjectMemberController {

  private final ProjectMemberService projectMemberService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public ProjectMemberDto createProjectMember(@RequestBody ProjectMemberDto projectMemberDto) {
    return projectMemberService.createProjectMember(projectMemberDto);
  }

  @GetMapping("/get/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectMemberDto getProjectMember(@PathVariable Long id) {
    return projectMemberService.getProjectMemberById(id);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public ProjectMemberDto updateProject(@RequestBody ProjectMemberDto projectMemberDto) {
    return projectMemberService.updateProjectMember(projectMemberDto);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectMemberDto deleteProject(@PathVariable Long id) {
    return projectMemberService.deleteProjectMember(id);
  }
}