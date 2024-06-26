package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
  private Integer id;
  private Date datetime;
  private Integer rating;
  private Integer userId;
  private String comment;
}
