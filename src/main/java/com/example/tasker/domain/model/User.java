package com.example.tasker.domain.model;

import com.example.tasker.domain.constants.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
