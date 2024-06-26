package com.server.controllers;

import com.server.domains.requests.event.QuestionFAQRequest;
import com.server.domains.responses.QuestionFAQResponse;
import com.server.services.QuestionFAQService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faqs")
public class QuestionFAQController {

  private final QuestionFAQService service;

  @GetMapping
  public ResponseEntity<List<QuestionFAQResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuestionFAQResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<QuestionFAQResponse> createQuestionFAQ(@RequestBody QuestionFAQRequest request) {
    return ResponseEntity.ok(service.createQuestionFAQ(request));
  }

  @PutMapping
  public ResponseEntity<QuestionFAQResponse> updateQuestionFAQ(@RequestBody QuestionFAQRequest request) {
    return ResponseEntity.ok(service.updateQuestionFAQ(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteQuestionFAQ(@PathVariable("id") Integer id) {
    service.deleteQuestionFAQ(id);
    return ResponseEntity.noContent().build();
  }
}
