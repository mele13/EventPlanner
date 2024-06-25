package com.server.domains;

import com.server.domains.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_credentials_auth")
public class CredentialsAuth {

  @Id
  @GeneratedValue()
  private Integer id;
  private String key;
  private String url;
  private Date expiration;

  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "credentials_id")
  private Credentials credentials;
}
