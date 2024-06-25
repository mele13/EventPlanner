package com.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domains.requests.event.CreateEventRequest;
import com.server.services.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  @PostMapping("/new")
  public ResponseEntity<String> createEvent(@RequestBody CreateEventRequest request) {
    eventService.createEvent(request);
    return ResponseEntity.ok().build();
  }
}
