package com.example.tasker.domain.model.mapper;

import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.TaskAttachment;
import com.example.tasker.domain.model.TaskHistory;
import com.example.tasker.domain.model.Token;
import com.example.tasker.domain.model.User;
import com.example.tasker.user.model.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(UserDto userDto);

//  @Mapping(target = "tokenIds", expression = "java(tokensToTokenIds(user.getTokens()))")
//  @Mapping(target = "projectMemberIds", expression = "java(projectMembersToProjectMemberIds(user.getProjectMembers()))")
//  @Mapping(target = "taskAttachmentIds", expression = "java(taskAttachmentsToTaskAttachmentIds(user.getTaskAttachments()))")
//  @Mapping(target = "taskHistoryIds", expression = "java(taskHistoriesToTaskHistoryIds(user.getTaskHistories()))")
//  @Mapping(target = "taskAssignedMemberIds", expression = "java(taskAssignedMembersToTaskAssignedMemberIds(user.getTaskAssignedMembers()))")
  UserDto toDto(User user);

  //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User updateUser(
      UserDto userDto, @MappingTarget User user);

//  default List<Long> tokensToTokenIds(List<Token> tokens) {
//    return tokens.stream().map(Token::getId).collect(Collectors.toList());
//  }
//
//  default List<Long> projectMembersToProjectMemberIds(List<ProjectMember> projectMembers) {
//    return projectMembers.stream().map(ProjectMember::getId).collect(Collectors.toList());
//  }
//
//  default List<Long> taskAttachmentsToTaskAttachmentIds(List<TaskAttachment> taskAttachments) {
//    return taskAttachments.stream().map(TaskAttachment::getId).collect(Collectors.toList());
//  }
//
//  default List<Long> taskHistoriesToTaskHistoryIds(List<TaskHistory> taskHistories) {
//    return taskHistories.stream().map(TaskHistory::getId).collect(Collectors.toList());
//  }
//
//  default List<Long> taskAssignedMembersToTaskAssignedMemberIds(
//      List<TaskAssigned> taskAssignedMembers) {
//    return taskAssignedMembers.stream().map(TaskAssigned::getId).collect(Collectors.toList());
//  }
}
