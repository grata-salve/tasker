package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

}