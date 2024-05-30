package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.Task;
import com.example.tasker.domain.model.TaskAssigned;
import com.example.tasker.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignedRepository extends JpaRepository<TaskAssigned, Long> {

  Optional<TaskAssigned> findByTaskAndMember(Optional<Task> task, Optional<User> user);
}
