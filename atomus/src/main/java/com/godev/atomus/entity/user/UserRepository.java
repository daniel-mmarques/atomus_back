package com.godev.atomus.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String username);

    @Query("""
        SELECT p.profile FROM Profile p
        JOIN User u ON p.id = u.id
        WHERE
        u.id = :userId
    """)
    String findProfileByUserId(Long userId);
}
