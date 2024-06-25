package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.EventMenu;

@Repository
public interface EventMenuRepository extends CrudRepository<EventMenu, Integer> {
  
}
