package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.CDate;
import com.server.domains.requests.event.CDateRequest;
import com.server.domains.responses.CDateResponse;
import com.server.mappers.CDateMapper;
import com.server.repositories.CDateRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CDateService {
  private final CDateRepository repository;

  public List<CDateResponse> getAll() {
    Iterable<CDate> cDates = repository.findAll();

    List<CDateResponse> cDateResponses = new ArrayList<>();
    cDates.forEach(cDate -> cDateResponses.add(CDateMapper.generateResponse(cDate)));
    return cDateResponses;
  }

  public CDateResponse getById(Integer id) {
    return CDateMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("CDate not found")));
  }

  public CDateResponse createCDate(CDateRequest request) {
    final CDate cDate = repository.save(CDateMapper.createCDate(request));
    return CDateMapper.generateResponse(cDate);
  }

  public CDateResponse updateCDate(CDateRequest request) {
    Optional<CDate> optionalCDate = repository.findById(request.getId());
    CDate existingCDate = optionalCDate.orElseThrow(() -> new IllegalArgumentException("Custom date not found"));

    final CDate updatedCDate = repository.save(CDateMapper.updateCDate(existingCDate, request));
    return CDateMapper.generateResponse(updatedCDate);
  }

  public void deleteCDate(Integer id) {
    repository.deleteById(id);
  }
}
