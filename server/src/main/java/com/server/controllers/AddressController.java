package com.server.controllers;

import com.server.domains.requests.location.AddressRequest;
import com.server.domains.responses.AddressResponse;
import com.server.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

  private final AddressService service;

  @GetMapping("/{id}")
  public ResponseEntity<AddressResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getAddressById(id));
  }

  @PostMapping
  public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest request) {
    return ResponseEntity.ok(service.createAddress(request));
  }

  @PutMapping()
  public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest request) {
    return ResponseEntity.ok(service.updateAddress(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer id) {
    service.deleteAddress(id);
    return ResponseEntity.noContent().build();
  }
}
