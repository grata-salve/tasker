package com.example.tasker.domain.repository;

import com.example.tasker.domain.constants.Role;
import com.example.tasker.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Modifying
  @Query("UPDATE User u SET u.role = :role WHERE u.id = :id")
  void updateUserRoleById(@Param("id") Long id, @Param("role") Role role);
}
