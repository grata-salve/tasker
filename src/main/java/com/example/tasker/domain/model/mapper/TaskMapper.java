package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.Task;
import com.example.tasker.task.model.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  Task toEntity(TaskDto taskDto);

  TaskDto toDto(Task task);

  Task updateTask(TaskDto taskDto, @MappingTarget Task task);
}
