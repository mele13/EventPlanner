package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {
  
}
