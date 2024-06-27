package com.server.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.mongo.EventChangeLog;

@Repository
public interface EventChangeLogRepository extends MongoRepository<EventChangeLog, String> {
  
}
