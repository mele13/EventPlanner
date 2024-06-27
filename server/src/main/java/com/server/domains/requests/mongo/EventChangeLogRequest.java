package com.server.domains.requests.mongo;

import java.util.Date;

import com.server.domains.enums.ChangeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventChangeLogRequest {
  private String eventId;
  private ChangeType changeType;
  private String fieldName;
  private String oldValue;
  private String newValue;
  private String changedBy;
  private Date timestamp;
}
