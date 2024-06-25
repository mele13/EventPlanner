package com.server.domains;

import java.util.List;

import com.server.domains.enums.CredentialType;

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
@Table(name = "credentials")
public class Credentials {
  
  @Id
  @GeneratedValue
  private Integer id;
  private CredentialType type;
  private String value;
  
  @OneToOne
  @JoinColumn(name= "event_id")
  private Event event;
  
  @OneToMany(mappedBy = "credentials", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CredentialsAuth> credentialsAuthList;
}
