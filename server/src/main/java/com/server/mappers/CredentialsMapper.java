package com.server.mappers;

import com.server.domains.Credentials;
import com.server.domains.requests.event.CredentialsRequest;
import com.server.domains.responses.CredentialsResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CredentialsMapper {
  public static CredentialsResponse generateResponse(Credentials credentials) {
    return CredentialsResponse.builder()
        .id(credentials.getId())
        .type(credentials.getType())
        .value(credentials.getValue())
        .build();
  }

  public static Credentials createCredentials(CredentialsRequest request) {
    return Credentials.builder()
        .id(null)
        .type(request.getType())
        .value(request.getValue())
        .build();
  }

  public static Credentials updateCredentials(Credentials credentials, CredentialsRequest request) {
    return Credentials.builder()
        .id(credentials.getId())
        .type(request.getType())
        .value(request.getValue())
        .build();
  }
}
