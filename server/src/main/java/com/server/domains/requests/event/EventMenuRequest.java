package com.server.domains.requests.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventMenuRequest {
  private Integer id;
  private String menuInfo;
  private String menuWarn;
  private Integer eventId;
  private Integer menuId;
}
