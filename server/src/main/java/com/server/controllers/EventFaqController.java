package com.server.controllers;

import com.server.domains.requests.event.EventFaqRequest;
import com.server.domains.responses.EventFaqResponse;
import com.server.services.EventFaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event-faqs")
public class EventFaqController {

  private final EventFaqService service;

  @GetMapping
  public ResponseEntity<List<EventFaqResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventFaqResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<EventFaqResponse> createEventFaq(@RequestBody EventFaqRequest request) {
    return ResponseEntity.ok(service.createEventFaq(request));
  }

  @PutMapping()
  public ResponseEntity<EventFaqResponse> updateEventFaq(@RequestBody EventFaqRequest request) {
    return ResponseEntity.ok(service.updateEventFaq(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEventFaq(@PathVariable("id") Integer id) {
    service.deleteEventFaq(id);
    return ResponseEntity.noContent().build();
  }
}
