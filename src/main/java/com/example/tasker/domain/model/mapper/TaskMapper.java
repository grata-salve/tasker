package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.task.model.TaskDto;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  @Mapping(source = "projectId", target = "project.id")
  Task toEntity(TaskDto taskDto);

  @Mapping(source = "project.id", target = "projectId")
  @Mapping(target = "taskAssignedMemberIds", expression = "java(taskAssignedMembersToTaskAssignedMemberIds(task.getTaskAssignedMembers()))")
  TaskDto toDto(Task task);

  default List<Long> taskAssignedMembersToTaskAssignedMemberIds(List<TaskAssigned> taskAssignedMembers) {
    return taskAssignedMembers == null ? Collections.emptyList() :
        taskAssignedMembers.stream().map(TaskAssigned::getId).collect(Collectors.toList());
  }

  Task updateTask(TaskDto taskDto, @MappingTarget Task task);
}
