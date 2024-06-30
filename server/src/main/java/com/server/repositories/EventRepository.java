package com.server.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
  List<Event> findByOwnerId(Integer id);
}
