package com.server.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.server.domains.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {
  Optional<Country> findByName(String name);
}
