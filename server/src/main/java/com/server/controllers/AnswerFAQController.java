package com.server.controllers;

import com.server.domains.requests.event.AnswerFAQRequest;
import com.server.domains.responses.AnswerFAQResponse;
import com.server.services.AnswerFAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer-faqs")
public class AnswerFAQController {

  private final AnswerFAQService service;

  @GetMapping
  public ResponseEntity<List<AnswerFAQResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AnswerFAQResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<AnswerFAQResponse> createAnswerFAQ(@RequestBody AnswerFAQRequest request) {
    return ResponseEntity.ok(service.createAnswerFAQ(request));
  }

  @PutMapping()
  public ResponseEntity<AnswerFAQResponse> updateAnswerFAQ(@RequestBody AnswerFAQRequest request) {
    return ResponseEntity.ok(service.updateAnswerFAQ(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnswerFAQ(@PathVariable("id") Integer id) {
    service.deleteAnswerFAQ(id);
    return ResponseEntity.noContent().build();
  }
}
