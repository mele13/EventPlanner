package com.server.domains.responses;

import com.server.domains.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsAuthResponse {
  private Integer id;
  private String key;
  private String url;
  private Date expiration;
  private Role role;
  private Integer userId;
}