package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.CredentialsAuth;
import com.server.domains.User;
import com.server.domains.requests.event.CredentialsAuthRequest;
import com.server.domains.responses.CredentialsAuthResponse;
import com.server.mappers.CredentialsAuthMapper;
import com.server.repositories.CredentialsAuthRepository;
import com.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CredentialsAuthService {
  private final CredentialsAuthRepository repository;
  private final UserRepository userRepository;

  public List<CredentialsAuthResponse> getAll() {
    Iterable<CredentialsAuth> credentialsAuths = repository.findAll();

    List<CredentialsAuthResponse> credentialsAuthResponses = new ArrayList<>();
    credentialsAuths.forEach(auth -> credentialsAuthResponses.add(CredentialsAuthMapper.generateResponse(auth)));
    return credentialsAuthResponses;
  }

  public CredentialsAuthResponse getById(Integer id) {
    return CredentialsAuthMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("CredentialsAuth not found")));
  }

  public CredentialsAuthResponse createCredentialsAuth(CredentialsAuthRequest request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    final CredentialsAuth credentialsAuth = repository
        .save(CredentialsAuthMapper.createCredentialsAuth(request, user));
    return CredentialsAuthMapper.generateResponse(credentialsAuth);
  }

  public CredentialsAuthResponse updateCredentialsAuth(CredentialsAuthRequest request) {
    Optional<CredentialsAuth> optionalCredentialsAuth = repository.findById(request.getId());
    CredentialsAuth existingCredentialsAuth = optionalCredentialsAuth
        .orElseThrow(() -> new IllegalArgumentException("CredentialsAuth not found"));

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    final CredentialsAuth updatedCredentialsAuth = repository
        .save(CredentialsAuthMapper.updateCredentialsAuth(existingCredentialsAuth, request, user));
    return CredentialsAuthMapper.generateResponse(updatedCredentialsAuth);
  }

  public void deleteCredentialsAuth(Integer id) {
    repository.deleteById(id);
  }
}
