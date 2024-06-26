package com.server.domains.requests.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
  private Integer id;
  private Date datetime;
  private Integer rating;
  private Integer userId;
  private String comment;
}
