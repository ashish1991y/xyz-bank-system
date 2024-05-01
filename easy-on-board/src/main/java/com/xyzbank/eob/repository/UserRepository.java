package com.xyzbank.eob.repository;

import com.xyzbank.eob.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    @EntityGraph(attributePaths = {"roles", "addresses", "accounts", "accounts.accountType", "accounts.currency", "accounts.documents", "loginHistories"})
    User findByUsername(String username);
}
