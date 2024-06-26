package com.server.domains.requests.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFaqRequest {
  private Integer id;
  private Integer eventId;
  private Integer questionId;
  private Integer answerId;
}
