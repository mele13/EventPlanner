package com.server.mappers;

import com.server.domains.AnswerFAQ;
import com.server.domains.requests.event.AnswerFAQRequest;
import com.server.domains.responses.AnswerFAQResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnswerFAQMapper {
  public static AnswerFAQResponse generateResponse(AnswerFAQ answerFAQ) {
    return AnswerFAQResponse.builder()
        .id(answerFAQ.getId())
        .answer(answerFAQ.getAnswer())
        .build();
  }

  public static AnswerFAQ createAnswerFAQ(AnswerFAQRequest request) {
    return AnswerFAQ.builder()
        .id(null)
        .answer(request.getAnswer())
        .build();
  }

  public static AnswerFAQ updateAnswerFAQ(AnswerFAQ answerFAQ, AnswerFAQRequest request) {
    return AnswerFAQ.builder()
        .id(answerFAQ.getId())
        .answer(request.getAnswer())
        .build();
  }
}
