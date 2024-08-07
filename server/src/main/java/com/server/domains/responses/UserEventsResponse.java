package com.server.domains.responses;

import com.server.domains.enums.EventType;
import com.server.domains.enums.UserEventRelationship;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEventsResponse {
  private Integer eventId;
  private Integer userId;
  private UserEventRelationship relationship;
  private String eventName;
  private String eventDescription;
  private EventType eventType;
}
