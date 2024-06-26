package com.server.controllers;

import com.server.domains.requests.location.VenueRequest;
import com.server.domains.responses.VenueResponse;
import com.server.services.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venues")
public class VenueController {

  private final VenueService venueService;

  @GetMapping
  public ResponseEntity<List<VenueResponse>> getAll() {
    return ResponseEntity.ok(venueService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<VenueResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(venueService.getById(id));
  }

  @PostMapping
  public ResponseEntity<VenueResponse> createVenue(@RequestBody VenueRequest request) {
    return ResponseEntity.ok(venueService.createVenue(request));
  }

  @PutMapping
  public ResponseEntity<VenueResponse> updateVenue(@RequestBody VenueRequest request) {
    return ResponseEntity.ok(venueService.updateVenue(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVenue(@PathVariable("id") Integer id) {
    venueService.deleteVenue(id);
    return ResponseEntity.noContent().build();
  }
}
