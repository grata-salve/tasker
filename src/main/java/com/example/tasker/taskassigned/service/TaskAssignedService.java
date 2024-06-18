package com.example.tasker.taskassigned.service;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.exception.GlobalException;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.mapper.TaskAssignedMapper;
import com.example.tasker.domain.repository.TaskAssignedRepository;
import com.example.tasker.domain.repository.TaskRepository;
import com.example.tasker.domain.repository.UserRepository;
import com.example.tasker.security.utils.SecurityUtils;
import com.example.tasker.taskassigned.model.TaskAssignedDto;
import com.example.tasker.taskhistory.service.TaskHistoryService;
import com.example.tasker.user.model.UserDto;
import com.example.tasker.user.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskAssignedService {

  private final TaskAssignedRepository taskAssignedRepository;
  private final TaskAssignedMapper taskAssignedMapper;
  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final UserService userService;
  private final TaskHistoryService taskHistoryService;

  @Transactional
  public TaskAssignedDto createTaskAssigned(TaskAssignedDto taskAssignedDto) {
    Optional<TaskAssigned> taskAssignedOptional = taskAssignedRepository.findByTaskAndMember(
        taskRepository.findById(taskAssignedDto.getTaskId()),
        userRepository.findById(taskAssignedDto.getMemberId()));

    if (taskAssignedOptional.isPresent()) {
      throw new GlobalException(HttpStatus.CONFLICT, "Task is already assigned to this user");
    }

    TaskAssigned taskAssigned = taskAssignedRepository.save(taskAssignedMapper.toEntity(taskAssignedDto));
    taskHistoryService.createTaskHistoryAuto(
        taskAssigned.getTask().getId(), SecurityUtils.getCurrentUser().getId(), Action.ASSIGNED);
    return taskAssignedMapper.toDto(taskAssigned);
  }

  @Transactional(readOnly = true)
  public TaskAssignedDto getTaskAssignedById(Long taskAssignedId) {
    TaskAssigned taskAssigned = taskAssignedRepository.findById(taskAssignedId).orElseThrow(
        NoSuchElementException::new);
    return taskAssignedMapper.toDto(taskAssigned);
  }

  @Transactional
  public TaskAssignedDto deleteTaskAssigned(Long taskAssignedId) {
    TaskAssigned taskAssigned = taskAssignedRepository.findById(taskAssignedId).orElseThrow(NoSuchElementException::new);
    taskAssignedRepository.deleteById(taskAssignedId);
    taskHistoryService.createTaskHistoryAuto(
            SecurityUtils.getCurrentUser().getId(), SecurityUtils.getCurrentUser().getId(), Action.UNASSIGNED);
    return taskAssignedMapper.toDto(taskAssigned);
  }

  @Transactional(readOnly = true)
  public List<UserDto> getMembersByTaskId(Long taskId) {
    List<Long> memberIds = taskAssignedRepository.findMemberIdsByTaskId(taskId);
    return memberIds.stream()
        .map(userService::getUserById)
        .collect(Collectors.toList());
  }
}