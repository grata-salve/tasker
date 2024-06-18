package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.TaskHistory;
import java.time.LocalDateTime;
import java.util.List;

import com.example.tasker.taskhistory.model.TaskHistoryWithComplexityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

  List<TaskHistory> findAllByTaskId(Long taskId);

  List<TaskHistory> findAllByMemberId(Long memberId);

  List<TaskHistory> findAllByMemberIdAndTaskId(Long memberId, Long taskId);

  @Query("SELECT th FROM TaskHistory th WHERE th.createdAt BETWEEN :startDate AND :endDate AND th.task.id = :taskId")
  List<TaskHistory> findAllByCreatedAtBetweenAndTaskId(
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate,
      @Param("taskId") Long taskId
  );

  @Query("SELECT th FROM TaskHistory th WHERE th.createdAt BETWEEN :startDate AND :endDate AND th.member.id = :memberId")
  List<TaskHistory> findAllByCreatedAtBetweenAndMemberId(
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate,
      @Param("memberId") Long memberId
  );

  @Query("SELECT new com.example.tasker.taskhistory.model.TaskHistoryWithComplexityDto(" +
          "th.id, th.createdAt, th.task.id, th.member.id, th.action, th.description, t.complexity) " +
          "FROM TaskHistory th " +
          "JOIN th.task t " +
          "WHERE th.member.id = :memberId")
  List<TaskHistoryWithComplexityDto> findByMemberIdWithTaskComplexity(@Param("memberId") Long memberId);

  @Query("SELECT new com.example.tasker.taskhistory.model.TaskHistoryWithComplexityDto(" +
          "th.id, th.createdAt, th.task.id, th.member.id, th.action, th.description, t.complexity) " +
          "FROM TaskHistory th " +
          "JOIN th.task t " +
          "WHERE t.project.id = :projectId")
  List<TaskHistoryWithComplexityDto> findByProjectIdWithTaskComplexity(@Param("projectId") Long projectId);
}