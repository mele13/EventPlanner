package com.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.domains.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
  
}
