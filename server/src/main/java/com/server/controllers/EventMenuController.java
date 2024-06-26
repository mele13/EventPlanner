package com.server.controllers;

import com.server.domains.requests.event.EventMenuRequest;
import com.server.domains.responses.EventMenuResponse;
import com.server.services.EventMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event-menus")
public class EventMenuController {

  private final EventMenuService service;

  @GetMapping
  public ResponseEntity<List<EventMenuResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventMenuResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<EventMenuResponse> createEventMenu(@RequestBody EventMenuRequest request) {
    return ResponseEntity.ok(service.createEventMenu(request));
  }

  @PutMapping()
  public ResponseEntity<EventMenuResponse> updateEventMenu(@RequestBody EventMenuRequest request) {
    return ResponseEntity.ok(service.updateEventMenu(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEventMenu(@PathVariable("id") Integer id) {
    service.deleteEventMenu(id);
    return ResponseEntity.noContent().build();
  }
}
