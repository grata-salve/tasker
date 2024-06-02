package com.example.tasker.projectmember.controller;

import com.example.tasker.projectmember.model.ProjectMemberDto;
import com.example.tasker.projectmember.service.ProjectMemberService;
import com.example.tasker.user.model.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

  @PreAuthorize("@projectSecurity.hasProjectRole(authentication, #projectMemberDto.projectId, 'PROJECT_ADMIN')")
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

  @PreAuthorize("@projectSecurity.hasAnyProjectRole(authentication, #projectMemberDto.projectId, {'PROJECT_ARCHITECT', 'PROJECT_MANAGER'})")
  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public ProjectMemberDto updateProject(@RequestBody ProjectMemberDto projectMemberDto) {
    return projectMemberService.updateProjectMember(projectMemberDto);
  }

  @PreAuthorize("@projectSecurity.hasProjectRole(authentication, #projectMemberDto.projectId, 'PROJECT_ADMIN')")
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProjectMemberDto deleteProject(@PathVariable Long id) {
    return projectMemberService.deleteProjectMember(id);
  }

  @GetMapping("/get/membersByProjectId/{projectId}")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> getMembersByProjectId(@PathVariable Long projectId) {
    return projectMemberService.getMembersByProjectId(projectId);
  }
}
