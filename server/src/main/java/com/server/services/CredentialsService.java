package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.Credentials;
import com.server.domains.requests.event.CredentialsRequest;
import com.server.domains.responses.CredentialsResponse;
import com.server.mappers.CredentialsMapper;
import com.server.repositories.CredentialsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CredentialsService {
  private final CredentialsRepository repository;

  public List<CredentialsResponse> getAll() {
    Iterable<Credentials> credentials = repository.findAll();

    List<CredentialsResponse> credentialsResponses = new ArrayList<>();
    credentials.forEach(cred -> credentialsResponses.add(CredentialsMapper.generateResponse(cred)));
    return credentialsResponses;
  }

  public CredentialsResponse getById(Integer id) {
    return CredentialsMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Credentials not found")));
  }

  public CredentialsResponse createCredentials(CredentialsRequest request) {
    final Credentials credentials = repository.save(CredentialsMapper.createCredentials(request));
    return CredentialsMapper.generateResponse(credentials);
  }

  public CredentialsResponse updateCredentials(CredentialsRequest request) {
    Optional<Credentials> optionalCredentials = repository.findById(request.getId());
    Credentials existingCredentials = optionalCredentials
        .orElseThrow(() -> new IllegalArgumentException("Credentials not found"));

    final Credentials updatedCredentials = repository
        .save(CredentialsMapper.updateCredentials(existingCredentials, request));
    return CredentialsMapper.generateResponse(updatedCredentials);
  }

  public void deleteCredentials(Integer id) {
    repository.deleteById(id);
  }
}
