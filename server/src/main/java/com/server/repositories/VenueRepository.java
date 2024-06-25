package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Venue;

@Repository
public interface VenueRepository extends CrudRepository<Venue, Integer> {
  
}
