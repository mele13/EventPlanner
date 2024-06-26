package com.server.domains.responses;

import com.server.domains.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private Integer id;
  private String name;
  private String username;
  private String email;
  private String alias;
  private String phone;
  private Role role;
}
