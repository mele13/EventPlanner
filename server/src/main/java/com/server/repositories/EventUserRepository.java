package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.EventUser;

@Repository
public interface EventUserRepository extends CrudRepository<EventUser, Integer> {
  
}
