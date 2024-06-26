package com.server.mappers;

import com.server.domains.QuestionFAQ;
import com.server.domains.requests.event.QuestionFAQRequest;
import com.server.domains.responses.QuestionFAQResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuestionFAQMapper {

  public static QuestionFAQResponse generateResponse(QuestionFAQ questionFAQ) {
    return QuestionFAQResponse.builder()
        .id(questionFAQ.getId())
        .question(questionFAQ.getQuestion())
        .build();
  }

  public static QuestionFAQ createQuestionFAQ(QuestionFAQRequest request) {
    return QuestionFAQ.builder()
        .question(request.getQuestion())
        .build();
  }

  public static QuestionFAQ updateQuestionFAQ(QuestionFAQ questionFAQ, QuestionFAQRequest request) {
    questionFAQ.setQuestion(request.getQuestion());
    return questionFAQ;
  }
}
