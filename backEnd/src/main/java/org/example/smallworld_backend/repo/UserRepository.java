package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    boolean existsByEmail(String userName);

    int deleteByEmail(String userName);

    @Modifying
    @Query(value = "UPDATE systemuser u SET u.password = ?2 WHERE u.first_name = ?1", nativeQuery = true)
    void updatepassword(String firstName, String password);

    @Modifying
    @Query(value = "UPDATE systemuser SET first_name = ?1, last_name = ?2, country = ?3, email = ?4, role = ?5 WHERE uid = ?6", nativeQuery = true)
    void updateUser(String firstName, String lastName, String country, String email, String role, UUID uid);

}