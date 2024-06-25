package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.QuestionFAQ;

@Repository
public interface QuestionFAQRepository extends CrudRepository<QuestionFAQ, Integer> {
  
}
