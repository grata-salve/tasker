package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.TaskHistory;
import java.time.LocalDateTime;
import java.util.List;
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
}