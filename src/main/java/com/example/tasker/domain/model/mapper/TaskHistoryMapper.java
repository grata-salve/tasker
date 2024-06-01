package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskHistory;
import com.example.tasker.taskhistory.model.TaskHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskHistoryMapper {

  @Mapping(source = "memberId", target = "member.id")
  @Mapping(source = "taskId", target = "task.id")
  TaskHistory toEntity(TaskHistoryDto taskHistoryDto);

  @Mapping(source = "member.id", target = "memberId")
  @Mapping(source = "task.id", target = "taskId")
  TaskHistoryDto toDto(TaskHistory taskHistory);

  TaskHistory updateTaskHistory(TaskHistoryDto taskHistoryDto,
      @MappingTarget TaskHistory taskHistory);
}
