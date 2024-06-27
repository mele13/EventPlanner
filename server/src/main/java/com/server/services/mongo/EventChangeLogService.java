package com.server.services.mongo;

import org.springframework.stereotype.Service;

import com.server.domains.mongo.EventChangeLog;
import com.server.domains.requests.mongo.EventChangeLogRequest;
import com.server.domains.responses.mongo.EventChangeLogResponse;
import com.server.mappers.mongo.EventChangeLogMapper;
import com.server.repositories.mongo.EventChangeLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventChangeLogService {
  private final EventChangeLogRepository repository;

    public EventChangeLogResponse addLogChange(EventChangeLogRequest request) {
      final EventChangeLog changeLog = repository.save(EventChangeLogMapper.createChangeLog(request));
      return EventChangeLogMapper.generateResponse(changeLog);
    }
}
