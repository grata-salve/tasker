package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignedRepository extends JpaRepository<TaskAssigned, Long> {

  Optional<TaskAssigned> findByTaskAndMember(Optional<Task> task, Optional<User> user);

  @Query("SELECT pm.member.id FROM TaskAssigned pm WHERE pm.task.id = :taskId")
  List<Long> findMemberIdsByTaskId(@Param("taskId") Long taskId);
}
