package com.example.tasker.taskassigned.service;

import com.example.tasker.domain.exception.GlobalException;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.mapper.TaskAssignedMapper;
import com.example.tasker.domain.repository.TaskAssignedRepository;
import com.example.tasker.domain.repository.TaskRepository;
import com.example.tasker.domain.repository.UserRepository;
import com.example.tasker.taskassigned.model.TaskAssignedDto;
import java.util.NoSuchElementException;
import java.util.Optional;
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

  @Transactional
  public TaskAssignedDto createTaskAssigned(TaskAssignedDto taskAssignedDto) {
    Optional<TaskAssigned> taskAssignedOptional = taskAssignedRepository.findByTaskAndMember(
        taskRepository.findById(taskAssignedDto.getTaskId()),
        userRepository.findById(taskAssignedDto.getMemberId()));

    if (taskAssignedOptional.isPresent()) {
      throw new GlobalException(HttpStatus.CONFLICT, "Ця задача вже призначена даному користувачу");
    }

    TaskAssigned taskAssigned = taskAssignedRepository.save(taskAssignedMapper.toEntity(taskAssignedDto));
    return taskAssignedMapper.toDto(taskAssigned);
  }

  @Transactional(readOnly = true)
  public TaskAssignedDto getTaskAssignedById(Long taskAssignedId) {
    TaskAssigned taskAssigned = taskAssignedRepository.findById(taskAssignedId).orElseThrow(
        NoSuchElementException::new);
    return taskAssignedMapper.toDto(taskAssigned);
  }

//  @Transactional
//  public TaskAssignedDto updateTaskAssigned(TaskAssignedDto taskAssignedDto) {
//    TaskAssigned taskAssigned = taskAssignedMapper.toEntity(taskAssignedDto);
//    return taskAssignedMapper.toDto(taskAssignedRepository.save(taskAssigned));
//  }

  @Transactional
  public TaskAssignedDto deleteTaskAssigned(Long taskAssignedId) {
    TaskAssigned taskAssigned = taskAssignedRepository.findById(taskAssignedId).orElseThrow(NoSuchElementException::new);
    taskAssignedRepository.deleteById(taskAssignedId);
    return taskAssignedMapper.toDto(taskAssigned);
  }
}

//TODO: update service method is not necessary; check if assigned before assignment -> custom exception