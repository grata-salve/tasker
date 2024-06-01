package com.example.tasker.taskhistory.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TimePeriodRequest {
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long taskId;
  private Long memberId;
}
