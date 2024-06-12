package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
  
}
