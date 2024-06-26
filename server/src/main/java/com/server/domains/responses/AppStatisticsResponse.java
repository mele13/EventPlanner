package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppStatisticsResponse {
  private Long totalUsers;
  private Long totalEvents;
}
