package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.DiscountCode;
import com.server.domains.requests.event.DiscountCodeRequest;
import com.server.domains.responses.DiscountCodeResponse;
import com.server.mappers.DiscountCodeMapper;
import com.server.repositories.DiscountCodeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountCodeService {
  private final DiscountCodeRepository repository;

  public List<DiscountCodeResponse> getAll() {
    Iterable<DiscountCode> discountCodes = repository.findAll();

    List<DiscountCodeResponse> discountCodeResponses = new ArrayList<>();
    discountCodes.forEach(code -> discountCodeResponses.add(DiscountCodeMapper.generateResponse(code)));
    return discountCodeResponses;
  }

  public DiscountCodeResponse getById(Integer id) {
    return DiscountCodeMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("DiscountCode not found")));
  }

  public DiscountCodeResponse createDiscountCode(DiscountCodeRequest request) {
    final DiscountCode discountCode = repository.save(DiscountCodeMapper.createDiscountCode(request));
    return DiscountCodeMapper.generateResponse(discountCode);
  }

  public DiscountCodeResponse updateDiscountCode(DiscountCodeRequest request) {
    Optional<DiscountCode> optionalDiscountCode = repository.findById(request.getId());
    DiscountCode existingDiscountCode = optionalDiscountCode
        .orElseThrow(() -> new IllegalArgumentException("DiscountCode not found"));

    final DiscountCode updatedDiscountCode = repository
        .save(DiscountCodeMapper.updateDiscountCode(existingDiscountCode, request));
    return DiscountCodeMapper.generateResponse(updatedDiscountCode);
  }

  public void deleteDiscountCode(Integer id) {
    repository.deleteById(id);
  }
}
