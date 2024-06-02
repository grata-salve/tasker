package com.example.tasker.domain.model;

import com.example.tasker.domain.constants.Role;
import com.example.tasker.domain.model.base.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  protected Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  @Column(name = "imagedata", length = 1000)
  private byte[] imageData;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
  private List<ProjectMember> projectMembers;

  @OneToMany(mappedBy = "member")
  private List<TaskAttachment> taskAttachments;

  @OneToMany(mappedBy = "member")
  private List<TaskHistory> taskHistories;

  @OneToMany(mappedBy = "member")
  private List<TaskAssigned> taskAssignedMembers;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>(role.getAuthorities());
    projectMembers.forEach(projectMember -> {
      String projectRoleWithProjectId =
          "PROJECT_" + projectMember.getRole().name() + "_" + projectMember.getProject().getId();
      authorities.add(new SimpleGrantedAuthority(projectRoleWithProjectId));
    });
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
