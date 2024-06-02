package com.example.tasker.projectmember.security;

import com.example.tasker.domain.model.User;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ProjectSecurity {

  public boolean hasProjectRole(Authentication authentication, Long projectId, String role) {
    User user = (User) authentication.getPrincipal();
    String requiredAuthority = role + "_" + projectId;
    return user.getAuthorities().stream()
        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(requiredAuthority));
  }

  public boolean hasAnyProjectRole(Authentication authentication, Long projectId, List<String> roles) {
    return roles.stream().anyMatch(role -> hasProjectRole(authentication, projectId, role));
  }
}

