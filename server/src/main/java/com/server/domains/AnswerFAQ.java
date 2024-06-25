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
@Table(name = "answer_faq")
public class AnswerFAQ {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String answer;
}
