package com.server.domains.requests.event;

import com.server.domains.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsAuthRequest {
  private Integer id;
  private String key;
  private String url;
  private Date expiration;
  private Role role;
  private Integer userId;
}
