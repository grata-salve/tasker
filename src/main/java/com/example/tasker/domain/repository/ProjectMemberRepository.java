package com.example.tasker.domain.repository;

import com.example.tasker.domain.model.Project;
import com.example.tasker.domain.model.ProjectMember;
import com.example.tasker.domain.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

  Optional<ProjectMember> findByProjectAndMember(Optional<Project> project, Optional<User> member);

  @Query("SELECT pm.member.id FROM ProjectMember pm WHERE pm.project.id = :projectId")
  List<Long> findMemberIdsByProjectId(@Param("projectId") Long projectId);
}