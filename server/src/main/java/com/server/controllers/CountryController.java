package com.server.controllers;

import com.server.domains.requests.location.CountryRequest;
import com.server.domains.responses.CountryResponse;
import com.server.services.CountryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService service;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryResponse> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<CountryResponse> createCountry(@RequestBody CountryRequest request) {
        return ResponseEntity.ok(service.createCountry(request));
    }

    @PutMapping()
    public ResponseEntity<CountryResponse> updateCountry(@RequestBody CountryRequest request) {
        return ResponseEntity.ok(service.updateCountry(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Integer id) {
        service.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
