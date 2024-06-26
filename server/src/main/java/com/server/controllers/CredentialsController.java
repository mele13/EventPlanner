package com.server.controllers;

import com.server.domains.requests.event.CredentialsRequest;
import com.server.domains.responses.CredentialsResponse;
import com.server.services.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/credentials")
public class CredentialsController {

  private final CredentialsService service;

  @GetMapping
  public ResponseEntity<List<CredentialsResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CredentialsResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CredentialsResponse> createCredentials(@RequestBody CredentialsRequest request) {
    return ResponseEntity.ok(service.createCredentials(request));
  }

  @PutMapping()
  public ResponseEntity<CredentialsResponse> updateCredentials(@RequestBody CredentialsRequest request) {
    return ResponseEntity.ok(service.updateCredentials(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCredentials(@PathVariable("id") Integer id) {
    service.deleteCredentials(id);
    return ResponseEntity.noContent().build();
  }
}
