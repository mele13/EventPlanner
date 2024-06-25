package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.CDate;

@Repository
public interface CDateRepository extends CrudRepository<CDate, Integer> {
  
}
