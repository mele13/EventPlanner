package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.EventFaq;

@Repository
public interface EventFaqRepository extends CrudRepository<EventFaq, Integer> {
  
}
