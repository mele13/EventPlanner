package com.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.server.domains.Token;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {
  
  @Query(value = """
                  SELECT t from Token t INNER JOIN User u\s
                  ON t.user.id = u.id\s
                  WHERE u.id = :id AND (t.expired = false or t.revoked = false)\s
                """)

  List<Token> findAllValidTokensByUser(Integer id);
  Optional<Token> findByJwt(String token);
}
