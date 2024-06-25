package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.AnswerFAQ;

@Repository
public interface AnswerFAQRepository extends CrudRepository<AnswerFAQ, Integer> {
  
}
