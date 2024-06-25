package com.server.domains.requests.user;

import com.server.domains.enums.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeRoleRequest {
  private Integer id;
  private Role role;
}
