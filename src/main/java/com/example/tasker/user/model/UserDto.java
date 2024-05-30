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
  private String password;
  private byte[] imageData;
  private Role role;
  private List<Long> tokenIds;
  private List<Long> projectMemberIds;
  private List<Long> taskAttachmentIds;
  private List<Long> taskHistoryIds;
  private List<Long> taskAssignedMemberIds;
}