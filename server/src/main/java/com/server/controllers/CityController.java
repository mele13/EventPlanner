package com.server.controllers;

import com.server.domains.requests.location.CityRequest;
import com.server.domains.responses.CityResponse;
import com.server.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

  private final CityService service;

  @GetMapping
  public ResponseEntity<List<CityResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CityResponse> getById(@RequestParam("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CityResponse> createCity(@RequestBody CityRequest request) {
    return ResponseEntity.ok(service.createCity(request));
  }

  @PutMapping()
  public ResponseEntity<CityResponse> updateCity(@RequestBody CityRequest request) {
    return ResponseEntity.ok(service.updateCity(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer id) {
    service.deleteCity(id);
    return ResponseEntity.noContent().build();
  }
}
