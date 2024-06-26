package com.server.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domains.requests.event.CDateRequest;
import com.server.domains.responses.CDateResponse;
import com.server.services.CDateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cdates")
public class CDateController {

  private final CDateService service;

  @GetMapping
  public ResponseEntity<List<CDateResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CDateResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CDateResponse> createCDate(@RequestBody CDateRequest request) {
    return ResponseEntity.ok(service.createCDate(request));
  }

  @PutMapping()
  public ResponseEntity<CDateResponse> updateCDate(@RequestBody CDateRequest request) {
    return ResponseEntity.ok(service.updateCDate(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCDate(@PathVariable("id") Integer id) {
    service.deleteCDate(id);
    return ResponseEntity.noContent().build();
  }
}
