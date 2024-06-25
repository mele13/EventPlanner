package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Credentials;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Integer> {
  
}
