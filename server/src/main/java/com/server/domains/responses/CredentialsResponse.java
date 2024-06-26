package com.server.domains.responses;

import com.server.domains.enums.CredentialType;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsResponse {
  private Integer id;
  private CredentialType type;
  private String value;
}
