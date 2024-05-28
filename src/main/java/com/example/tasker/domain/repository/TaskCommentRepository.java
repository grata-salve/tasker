package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {

}