package com.server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.server.domains.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {
  
}
