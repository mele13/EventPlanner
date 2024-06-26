package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.EventFaq;
import com.server.domains.Event;
import com.server.domains.QuestionFAQ;
import com.server.domains.requests.event.EventFaqRequest;
import com.server.domains.AnswerFAQ;
import com.server.domains.responses.EventFaqResponse;
import com.server.mappers.EventFaqMapper;
import com.server.repositories.AnswerFAQRepository;
import com.server.repositories.EventFaqRepository;
import com.server.repositories.EventRepository;
import com.server.repositories.QuestionFAQRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventFaqService {
  private final EventFaqRepository repository;
  private final EventRepository eventRepository;
  private final QuestionFAQRepository questionRepository;
  private final AnswerFAQRepository answerRepository;

  public List<EventFaqResponse> getAll() {
    Iterable<EventFaq> eventFaqs = repository.findAll();

    List<EventFaqResponse> eventFaqResponses = new ArrayList<>();
    eventFaqs.forEach(faq -> eventFaqResponses.add(EventFaqMapper.generateResponse(faq)));
    return eventFaqResponses;
  }

  public EventFaqResponse getById(Integer id) {
    return EventFaqMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("EventFaq not found")));
  }

  public EventFaqResponse createEventFaq(EventFaqRequest request) {
    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    QuestionFAQ question = questionRepository.findById(request.getQuestionId())
        .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    AnswerFAQ answer = answerRepository.findById(request.getAnswerId())
        .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

    final EventFaq eventFaq = repository.save(EventFaqMapper.createEventFaq(request, event, question, answer));
    return EventFaqMapper.generateResponse(eventFaq);
  }

  public EventFaqResponse updateEventFaq(EventFaqRequest request) {
    Optional<EventFaq> optionalEventFaq = repository.findById(request.getId());
    EventFaq existingEventFaq = optionalEventFaq.orElseThrow(() -> new IllegalArgumentException("EventFaq not found"));

    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    QuestionFAQ question = questionRepository.findById(request.getQuestionId())
        .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    AnswerFAQ answer = answerRepository.findById(request.getAnswerId())
        .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

    final EventFaq updatedEventFaq = repository
        .save(EventFaqMapper.updateEventFaq(existingEventFaq, request, event, question, answer));
    return EventFaqMapper.generateResponse(updatedEventFaq);
  }

  public void deleteEventFaq(Integer id) {
    repository.deleteById(id);
  }
}
