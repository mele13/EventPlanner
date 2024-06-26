package com.server.mappers;

import com.server.domains.CredentialsAuth;
import com.server.domains.User;
import com.server.domains.requests.event.CredentialsAuthRequest;
import com.server.domains.responses.CredentialsAuthResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CredentialsAuthMapper {
  public static CredentialsAuthResponse generateResponse(CredentialsAuth credentialsAuth) {
    return CredentialsAuthResponse.builder()
        .id(credentialsAuth.getId())
        .key(credentialsAuth.getKey())
        .url(credentialsAuth.getUrl())
        .expiration(credentialsAuth.getExpiration())
        .role(credentialsAuth.getRole())
        .userId(credentialsAuth.getUser() != null ? credentialsAuth.getUser().getId() : null)
        .build();
  }

  public static CredentialsAuth createCredentialsAuth(CredentialsAuthRequest request, User user) {
    return CredentialsAuth.builder()
        .id(null)
        .key(request.getKey())
        .url(request.getUrl())
        .expiration(request.getExpiration())
        .role(request.getRole())
        .user(user)
        .build();
  }

  public static CredentialsAuth updateCredentialsAuth(CredentialsAuth credentialsAuth, CredentialsAuthRequest request,
      User user) {
    return CredentialsAuth.builder()
        .id(credentialsAuth.getId())
        .key(request.getKey())
        .url(request.getUrl())
        .expiration(request.getExpiration())
        .role(request.getRole())
        .user(user)
        .build();
  }
}
