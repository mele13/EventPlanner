package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventMenuResponse {
  private Integer id;
  private String menuInfo;
  private String menuWarn;
  private Integer eventId;
  private Integer menuId;
}
