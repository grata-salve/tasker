package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskComment;
import com.example.tasker.taskcomment.model.TaskCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

  @Mapping(source = "userId", target = "user.id")
  @Mapping(source = "taskId", target = "task.id")
  TaskComment toEntity(TaskCommentDto taskCommentDto);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "task.id", target = "taskId")
  TaskCommentDto toDto(TaskComment taskComment);

  TaskComment updateTaskComment(TaskCommentDto taskCommentDto,
      @MappingTarget TaskComment taskComment);
}
