package com.server.controllers;

import com.server.domains.requests.GeneralRequest;
import com.server.domains.requests.event.EventRequest;
import com.server.domains.responses.EventResponse;
import com.server.domains.responses.EventStatisticsResponse;
import com.server.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

  private final EventService service;

  @GetMapping
  public ResponseEntity<List<EventResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request) {
    return ResponseEntity.ok(service.createEvent(request));
  }

  @PutMapping()
  public ResponseEntity<EventResponse> updateEvent(@RequestBody EventRequest request) {
    return ResponseEntity.ok(service.updateEvent(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEvent(GeneralRequest request) {
    service.deleteEvent(request);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/statistics")
  public EventStatisticsResponse getEventStatistics(@PathVariable("id") Integer id) {
    return service.getEventStatistics(id);
  }
}
