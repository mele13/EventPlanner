package com.server.controllers;

import com.server.domains.requests.event.MenuRequest;
import com.server.domains.responses.MenuResponse;
import com.server.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

  private final MenuService service;

  @GetMapping
  public ResponseEntity<List<MenuResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MenuResponse> getById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest request) {
    return ResponseEntity.ok(service.createMenu(request));
  }

  @PutMapping
  public ResponseEntity<MenuResponse> updateMenu(@RequestBody MenuRequest request) {
    return ResponseEntity.ok(service.updateMenu(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMenu(@PathVariable("id") Integer id) {
    service.deleteMenu(id);
    return ResponseEntity.noContent().build();
  }
}
