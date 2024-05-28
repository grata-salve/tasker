package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

}