package com.server.mappers.mongo;

import com.server.domains.enums.ChangeType;
import com.server.domains.mongo.EventChangeLog;
import com.server.domains.requests.mongo.EventChangeLogRequest;
import com.server.domains.responses.mongo.EventChangeLogResponse;

import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventChangeLogMapper {
  public static EventChangeLogResponse generateResponse(EventChangeLog changeLog) {
    return EventChangeLogResponse.builder()
        .eventId(changeLog.getEventId())
        .changeType(changeLog.getChangeType())
        .fieldName(changeLog.getFieldName())
        .oldValue(changeLog.getOldValue())
        .newValue(changeLog.getNewValue())
        .timestamp(changeLog.getTimestamp())
        .changedBy(changeLog.getChangedBy())
        .build();
  }

  public static EventChangeLog createChangeLog(EventChangeLogRequest request) {
    return EventChangeLog.builder()
        .eventId(null)
        .changeType(request.getChangeType())
        .fieldName(request.getFieldName())
        .oldValue(request.getOldValue())
        .newValue(request.getNewValue())
        .timestamp(request.getTimestamp())
        .changedBy(request.getChangedBy())
        .build();
  }

  public static EventChangeLog updateChangeLog(EventChangeLog changeLog, EventChangeLogRequest request) {
    return EventChangeLog.builder()
        .eventId(request.getEventId())
        .changeType(request.getChangeType())
        .fieldName(request.getFieldName())
        .oldValue(request.getOldValue())
        .newValue(request.getNewValue())
        .timestamp(request.getTimestamp())
        .changedBy(request.getChangedBy())
        .build();
  }

  public static EventChangeLogRequest newRequest(String eventId, ChangeType changeType, String fieldName,
    String oldValue, String newValue, String changedBy, Date timestamp) {
    return EventChangeLogRequest.builder()
        .eventId(eventId)
        .changeType(changeType)
        .fieldName(fieldName)
        .oldValue(oldValue)
        .newValue(newValue)
        .timestamp(timestamp)
        .changedBy(changedBy)
        .build();
  }
}
