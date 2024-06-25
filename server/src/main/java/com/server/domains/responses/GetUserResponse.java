package com.server.domains.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.domains.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

  @JsonProperty
  private Integer id;

  @JsonProperty
  private String name;

  @JsonProperty
  private String username;

  @JsonProperty
  private String email;

  @JsonProperty
  private String alias;

  @JsonProperty
  private String phone;

  @JsonProperty
  private Role role;
}
