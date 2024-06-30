package com.server.domains.responses;

import com.server.domains.enums.UserEventRelationship;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEventsResponse {
  private Integer eventId;
  private UserEventRelationship relationship;
  private String eventName;
  private String eventDescription;
}
