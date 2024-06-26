package com.server.domains.requests.event;

import com.server.domains.enums.CredentialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRequest {
  private Integer id;
  private CredentialType type;
  private String value;
}
