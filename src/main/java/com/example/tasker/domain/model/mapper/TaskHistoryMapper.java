package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskHistory;
import com.example.tasker.taskhistory.model.TaskHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskHistoryMapper {

  TaskHistory toEntity(TaskHistoryDto taskHistoryDto);

  TaskHistoryDto toDto(TaskHistory taskHistory);

  TaskHistory updateTaskHistory(TaskHistoryDto taskHistoryDto,
      @MappingTarget TaskHistory taskHistory);
}
