package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskComment;
import com.example.tasker.taskcomment.model.TaskCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

  TaskComment toEntity(TaskCommentDto taskCommentDto);

  TaskCommentDto toDto(TaskComment taskComment);

  TaskComment updateTaskComment(TaskCommentDto taskCommentDto,
      @MappingTarget TaskComment taskComment);
}
