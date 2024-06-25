package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
  
}
