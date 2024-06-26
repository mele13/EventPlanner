package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventStatisticsResponse {
  private Integer eventId;
  private Long attendeesCount;
  private Long commentsCount;
  private Long pageViews;
}
