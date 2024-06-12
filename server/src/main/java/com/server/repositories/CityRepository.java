package com.server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.server.domains.City;

public interface CityRepository extends CrudRepository<City, Integer> {
  
}
