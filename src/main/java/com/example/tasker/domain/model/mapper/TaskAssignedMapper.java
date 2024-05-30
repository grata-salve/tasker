package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.taskassigned.model.TaskAssignedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskAssignedMapper {

  @Mapping(source = "taskId", target = "task.id")
  @Mapping(source = "memberId", target = "member.id")
  TaskAssigned toEntity(TaskAssignedDto taskAssignedDto);

  @Mapping(source = "task.id", target = "taskId")
  @Mapping(source = "member.id", target = "memberId")
  TaskAssignedDto toDto(TaskAssigned taskAssigned);

  TaskAssigned updateTaskAssigned(TaskAssignedDto taskAssignedDto,
      @MappingTarget TaskAssigned taskAssigned);
}
