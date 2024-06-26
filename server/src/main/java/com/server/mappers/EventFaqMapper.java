package com.server.mappers;

import com.server.domains.EventFaq;
import com.server.domains.Event;
import com.server.domains.QuestionFAQ;
import com.server.domains.requests.event.EventFaqRequest;
import com.server.domains.AnswerFAQ;
import com.server.domains.responses.EventFaqResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventFaqMapper {
  public static EventFaqResponse generateResponse(EventFaq eventFaq) {
    return EventFaqResponse.builder()
        .id(eventFaq.getId())
        .eventId(eventFaq.getEvent() != null ? eventFaq.getEvent().getId() : null)
        .questionId(eventFaq.getQuestion() != null ? eventFaq.getQuestion().getId() : null)
        .answerId(eventFaq.getAnswer() != null ? eventFaq.getAnswer().getId() : null)
        .build();
  }

  public static EventFaq createEventFaq(EventFaqRequest request, Event event, QuestionFAQ question, AnswerFAQ answer) {
    return EventFaq.builder()
        .id(null)
        .event(event)
        .question(question)
        .answer(answer)
        .build();
  }

  public static EventFaq updateEventFaq(EventFaq eventFaq, EventFaqRequest request, Event event, QuestionFAQ question,
      AnswerFAQ answer) {
    return EventFaq.builder()
        .id(eventFaq.getId())
        .event(event)
        .question(question)
        .answer(answer)
        .build();
  }
}
