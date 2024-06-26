package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.AnswerFAQ;
import com.server.domains.requests.event.AnswerFAQRequest;
import com.server.domains.responses.AnswerFAQResponse;
import com.server.mappers.AnswerFAQMapper;
import com.server.repositories.AnswerFAQRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerFAQService {
  private final AnswerFAQRepository repository;

  public List<AnswerFAQResponse> getAll() {
    Iterable<AnswerFAQ> answerFAQs = repository.findAll();

    List<AnswerFAQResponse> answerFAQResponses = new ArrayList<>();
    answerFAQs.forEach(answerFAQ -> answerFAQResponses.add(AnswerFAQMapper.generateResponse(answerFAQ)));
    return answerFAQResponses;
  }

  public AnswerFAQResponse getById(Integer id) {
    return AnswerFAQMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("AnswerFAQ not found")));
  }

  public AnswerFAQResponse createAnswerFAQ(AnswerFAQRequest request) {
    final AnswerFAQ answerFAQ = repository.save(AnswerFAQMapper.createAnswerFAQ(request));
    return AnswerFAQMapper.generateResponse(answerFAQ);
  }

  public AnswerFAQResponse updateAnswerFAQ(AnswerFAQRequest request) {
    Optional<AnswerFAQ> optionalAnswerFAQ = repository.findById(request.getId());
    AnswerFAQ existingAnswerFAQ = optionalAnswerFAQ
        .orElseThrow(() -> new IllegalArgumentException("AnswerFAQ not found"));

    final AnswerFAQ updatedAnswerFAQ = repository.save(AnswerFAQMapper.updateAnswerFAQ(existingAnswerFAQ, request));
    return AnswerFAQMapper.generateResponse(updatedAnswerFAQ);
  }

  public void deleteAnswerFAQ(Integer id) {
    repository.deleteById(id);
  }
}
