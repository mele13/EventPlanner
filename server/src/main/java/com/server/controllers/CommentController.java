package com.server.controllers;

import com.server.domains.requests.event.CommentRequest;
import com.server.domains.responses.CommentResponse;
import com.server.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

  private final CommentService service;

  @GetMapping
  public ResponseEntity<List<CommentResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request) {
    return ResponseEntity.ok(service.createComment(request));
  }

  @PutMapping()
  public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentRequest request) {
    return ResponseEntity.ok(service.updateComment(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id) {
    service.deleteComment(id);
    return ResponseEntity.noContent().build();
  }
}
