package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.TaskAttachment;
import com.example.tasker.taskattachment.model.TaskAttachmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskAttachmentMapper {

  @Mapping(source = "memberId", target = "member.id")
  @Mapping(source = "taskId", target = "task.id")
  TaskAttachment toEntity(TaskAttachmentDto taskAttachmentDto);

  @Mapping(source = "member.id", target = "memberId")
  @Mapping(source = "task.id", target = "taskId")
  TaskAttachmentDto toDto(TaskAttachment taskAttachment);

  TaskAttachment updateTaskAttachment(TaskAttachmentDto taskAttachmentDto,
      @MappingTarget TaskAttachment taskAttachment);
}
