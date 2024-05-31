package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // SELECT * FROM user WHERE name = ? 인 SQL문법과 동일함.
    Optional<User> findByName(String name);
}
