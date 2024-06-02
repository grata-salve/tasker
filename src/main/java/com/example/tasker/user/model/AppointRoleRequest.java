package com.example.tasker.user.model;

import com.example.tasker.domain.constants.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointRoleRequest {

  @NotNull
  Long userId;
  @NotNull
  Role role;
}
