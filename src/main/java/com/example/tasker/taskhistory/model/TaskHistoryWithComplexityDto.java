package com.example.tasker.taskhistory.model;

import com.example.tasker.domain.constants.Action;
import com.example.tasker.domain.model.TaskHistory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link TaskHistory} entity
 */
@Data
@AllArgsConstructor
public class TaskHistoryWithComplexityDto {

    private Long id;
    private LocalDateTime createdAt;
    private Long taskId;
    private Long memberId;
    @NotNull
    private Action action;
    private String description;
    private int complexity;
}
