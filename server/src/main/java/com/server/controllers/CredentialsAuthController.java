package com.server.controllers;

import com.server.domains.requests.event.CredentialsAuthRequest;
import com.server.domains.responses.CredentialsAuthResponse;
import com.server.services.CredentialsAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/credentials-auth")
public class CredentialsAuthController {

  private final CredentialsAuthService service;

  @GetMapping
  public ResponseEntity<List<CredentialsAuthResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CredentialsAuthResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CredentialsAuthResponse> createCredentialsAuth(@RequestBody CredentialsAuthRequest request) {
    return ResponseEntity.ok(service.createCredentialsAuth(request));
  }

  @PutMapping()
  public ResponseEntity<CredentialsAuthResponse> updateCredentialsAuth(@RequestBody CredentialsAuthRequest request) {
    return ResponseEntity.ok(service.updateCredentialsAuth(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCredentialsAuth(@PathVariable("id") Integer id) {
    service.deleteCredentialsAuth(id);
    return ResponseEntity.noContent().build();
  }
}
