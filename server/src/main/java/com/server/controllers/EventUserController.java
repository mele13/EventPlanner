package com.server.controllers;

import com.server.domains.requests.event.EventUserRequest;
import com.server.domains.responses.EventUserResponse;
import com.server.services.EventUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event-users")
public class EventUserController {

  private final EventUserService service;

  @GetMapping
  public ResponseEntity<List<EventUserResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventUserResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<EventUserResponse> createEventUser(@RequestBody EventUserRequest request) {
    return ResponseEntity.ok(service.createEventUser(request));
  }

  @PutMapping()
  public ResponseEntity<EventUserResponse> updateEventUser(@RequestBody EventUserRequest request) {
    return ResponseEntity.ok(service.updateEventUser(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEventUser(@PathVariable("id") Integer id) {
    service.deleteEventUser(id);
    return ResponseEntity.noContent().build();
  }

}
