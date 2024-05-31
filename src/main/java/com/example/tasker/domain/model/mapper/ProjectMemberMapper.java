package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.projectmember.model.ProjectMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

  @Mapping(source = "projectId", target = "project.id")
  @Mapping(source = "memberId", target = "member.id")
  ProjectMember toEntity(ProjectMemberDto projectMemberDto);

  @Mapping(source = "project.id", target = "projectId")
  @Mapping(source = "member.id", target = "memberId")
  ProjectMemberDto toDto(ProjectMember projectMember);

  void updateProjectMember(ProjectMemberDto projectMemberDto,
      @MappingTarget ProjectMember projectMember);
}
