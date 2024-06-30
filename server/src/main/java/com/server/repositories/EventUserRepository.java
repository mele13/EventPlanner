package com.server.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.EventUser;

@Repository
public interface EventUserRepository extends CrudRepository<EventUser, Integer> {
  List<EventUser> findByUserId(Integer id);
}
