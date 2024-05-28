package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskAttachment;
import com.example.tasker.taskattachment.model.TaskAttachmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskAttachmentMapper {

  TaskAttachment toEntity(TaskAttachmentDto taskAttachmentDto);

  TaskAttachmentDto toDto(TaskAttachment taskAttachment);

  TaskAttachment updateTaskAttachment(TaskAttachmentDto taskAttachmentDto,
      @MappingTarget TaskAttachment taskAttachment);
}
