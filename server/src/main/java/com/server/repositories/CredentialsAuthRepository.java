package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.CredentialsAuth;

@Repository
public interface CredentialsAuthRepository extends CrudRepository<CredentialsAuth, Integer> {
  
}
