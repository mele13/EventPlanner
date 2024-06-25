package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.DiscountCode;

@Repository
public interface DiscountCodeRepository extends CrudRepository<DiscountCode, Integer> {
  
}
