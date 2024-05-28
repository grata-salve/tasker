package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.projectmember.model.ProjectMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

  ProjectMember toEntity(ProjectMemberDto projectMemberDto);

  ProjectMemberDto toDto(ProjectMember projectMember);

  void updateProjectMember(ProjectMemberDto projectMemberDto,
      @MappingTarget ProjectMember projectMember);
}
