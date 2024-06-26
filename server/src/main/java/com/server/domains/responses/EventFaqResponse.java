package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFaqResponse {
  private Integer id;
  private Integer eventId;
  private Integer questionId;
  private Integer answerId;
}
