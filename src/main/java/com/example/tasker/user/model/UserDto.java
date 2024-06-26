package com.example.tasker.user.model;

import com.example.tasker.domain.constants.Role;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * A DTO for the {@link com.example.tasker.domain.model.User} entity
 */
@Data
public class UserDto implements Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private byte[] imageData;
  private Role role;
}