package com.example.tasker.projectmember.service;

import com.example.tasker.domain.constants.ProjectRole;
import com.example.tasker.domain.exception.GlobalException;
import com.example.tasker.domain.model.Project;
import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.mapper.ProjectMemberMapper;
import com.example.tasker.domain.repository.ProjectMemberRepository;
import com.example.tasker.domain.repository.ProjectRepository;
import com.example.tasker.domain.repository.UserRepository;
import com.example.tasker.projectmember.model.ProjectMemberDto;
import com.example.tasker.user.model.UserDto;
import com.example.tasker.user.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

  private final ProjectMemberRepository projectMemberRepository;
  private final ProjectMemberMapper projectMemberMapper;
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final UserService userService;

  @Transactional
  public ProjectMemberDto createProjectMember(ProjectMemberDto projectMemberDto) {
    Optional<ProjectMember> projectMemberOptional = projectMemberRepository.findByProjectAndMember(
        projectRepository.findById(projectMemberDto.getProjectId()),
        userRepository.findById(projectMemberDto.getMemberId()));

    if (projectMemberOptional.isPresent()) {
      throw new GlobalException(HttpStatus.CONFLICT, "User already appointed");
    }

    ProjectMember projectMember = projectMemberRepository.save(projectMemberMapper.toEntity(projectMemberDto));
    return projectMemberMapper.toDto(projectMember);
  }

  @Transactional(readOnly = true)
  public ProjectMemberDto getProjectMemberById(Long projectMemberId) {
    ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow(NoSuchElementException::new);
    return projectMemberMapper.toDto(projectMember);
  }

  @Transactional
  public ProjectMemberDto updateProjectMember(ProjectMemberDto projectMemberDto) {
    ProjectMember projectMember = projectMemberMapper.toEntity(projectMemberDto);
    return projectMemberMapper.toDto(projectMemberRepository.save(projectMember));
  }

  @Transactional
  public ProjectMemberDto deleteProjectMember(Long projectMemberId) {
    ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow(NoSuchElementException::new);
    projectMemberRepository.deleteById(projectMemberId);
    return projectMemberMapper.toDto(projectMember);
  }

  @Transactional(readOnly = true)
  public List<UserDto> getMembersByProjectId(Long projectId) {
    List<Long> memberIds = projectMemberRepository.findMemberIdsByProjectId(projectId);
    return memberIds.stream()
        .map(userService::getUserById)
        .collect(Collectors.toList());
  }

  public ProjectRole getRoleForUserInProject(Long userId, Long projectId) {
    return projectMemberRepository.findByMemberIdAndProjectId(userId, projectId)
        .map(ProjectMember::getRole).orElseThrow(NoSuchElementException::new);
  }
}