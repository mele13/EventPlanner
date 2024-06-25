package com.server.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_faq")
public class EventFaq {
  
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private QuestionFAQ question;

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private AnswerFAQ answer;

}
