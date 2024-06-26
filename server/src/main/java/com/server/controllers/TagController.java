package com.server.controllers;

import com.server.domains.requests.event.TagRequest;
import com.server.domains.responses.TagResponse;
import com.server.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

  private final TagService tagService;

  @GetMapping
  public ResponseEntity<List<TagResponse>> getAll() {
    return ResponseEntity.ok(tagService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TagResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(tagService.getById(id));
  }

  @PostMapping
  public ResponseEntity<TagResponse> createTag(@RequestBody TagRequest request) {
    return ResponseEntity.ok(tagService.createTag(request));
  }

  @PutMapping
  public ResponseEntity<TagResponse> updateTag(@RequestBody TagRequest request) {
    return ResponseEntity.ok(tagService.updateTag(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable("id") Integer id) {
    tagService.deleteTag(id);
    return ResponseEntity.noContent().build();
  }
}
