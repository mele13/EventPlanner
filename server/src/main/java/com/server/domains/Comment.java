package com.server.domains;

import java.util.Date;

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
@Table(name = "comment")
public class Comment {
  
  @Id
  @GeneratedValue
  private Integer id;
  private Date datetime;
  private Integer rating;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Lob
  private String comment;  
}
