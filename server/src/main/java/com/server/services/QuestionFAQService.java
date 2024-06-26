package com.server.services;

import com.server.domains.QuestionFAQ;
import com.server.domains.requests.event.QuestionFAQRequest;
import com.server.domains.responses.QuestionFAQResponse;
import com.server.mappers.QuestionFAQMapper;
import com.server.repositories.QuestionFAQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionFAQService {

  private final QuestionFAQRepository repository;

  public List<QuestionFAQResponse> getAll() {
    Iterable<QuestionFAQ> questionFAQs = repository.findAll();

    List<QuestionFAQResponse> questionFAQResponses = new ArrayList<>();
    questionFAQs.forEach(questionFAQ -> questionFAQResponses.add(QuestionFAQMapper.generateResponse(questionFAQ)));
    return questionFAQResponses;
  }

  public QuestionFAQResponse getById(Integer id) {
    return QuestionFAQMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("QuestionFAQ not found")));
  }

  public QuestionFAQResponse createQuestionFAQ(QuestionFAQRequest request) {
    QuestionFAQ questionFAQ = repository.save(QuestionFAQMapper.createQuestionFAQ(request));
    return QuestionFAQMapper.generateResponse(questionFAQ);
  }

  public QuestionFAQResponse updateQuestionFAQ(QuestionFAQRequest request) {
    Optional<QuestionFAQ> optionalQuestionFAQ = repository.findById(request.getId());
    QuestionFAQ existingQuestionFAQ = optionalQuestionFAQ
        .orElseThrow(() -> new IllegalArgumentException("QuestionFAQ not found"));

    QuestionFAQ updatedQuestionFAQ = QuestionFAQMapper.updateQuestionFAQ(existingQuestionFAQ, request);
    updatedQuestionFAQ = repository.save(updatedQuestionFAQ);
    return QuestionFAQMapper.generateResponse(updatedQuestionFAQ);
  }

  public void deleteQuestionFAQ(Integer id) {
    repository.deleteById(id);
  }
}