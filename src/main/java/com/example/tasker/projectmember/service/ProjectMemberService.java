package com.example.tasker.projectmember.service;

import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.domain.model.mapper.ProjectMemberMapper;
import com.example.tasker.domain.repository.ProjectMemberRepository;
import com.example.tasker.projectmember.model.ProjectMemberDto;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

  private final ProjectMemberRepository projectMemberRepository;
  private final ProjectMemberMapper projectMemberMapper;

  @Transactional
  public ProjectMemberDto createProjectMember(ProjectMemberDto projectMemberDto) {
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
}
