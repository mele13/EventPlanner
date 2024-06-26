package com.server.controllers;

import com.server.domains.requests.event.DiscountCodeRequest;
import com.server.domains.responses.DiscountCodeResponse;
import com.server.services.DiscountCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount-codes")
public class DiscountCodeController {

  private final DiscountCodeService service;

  @GetMapping
  public ResponseEntity<List<DiscountCodeResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiscountCodeResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<DiscountCodeResponse> createDiscountCode(@RequestBody DiscountCodeRequest request) {
    return ResponseEntity.ok(service.createDiscountCode(request));
  }

  @PutMapping()
  public ResponseEntity<DiscountCodeResponse> updateDiscountCode(@RequestBody DiscountCodeRequest request) {
    return ResponseEntity.ok(service.updateDiscountCode(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDiscountCode(@PathVariable("id") Integer id) {
    service.deleteDiscountCode(id);
    return ResponseEntity.noContent().build();
  }
}
