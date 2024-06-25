package com.server.domains;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.domains.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"", uniqueConstraints = {
  @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {

  @Id
  @GeneratedValue()
  private Integer id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  private String name;
  private String surname;
  private String email;
  private String password;
  private String alias; // Username - UserDetails "reserved" (user...), thus alias
  private String phone;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Lob
  private byte[] image;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

  @OneToMany
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    // Ensuring UserDetails getUsername() for JWT auth
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
