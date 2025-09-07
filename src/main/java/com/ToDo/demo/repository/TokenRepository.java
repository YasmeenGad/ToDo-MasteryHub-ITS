package com.ToDo.demo.repository;

import com.ToDo.demo.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
            select t from Token t
            where t.user.id = :userId and t.loggedOut = false
            """)
    List<Token> findAllTokensByUser(Integer userId);

    Optional<Token> findByToken(String token);
}
