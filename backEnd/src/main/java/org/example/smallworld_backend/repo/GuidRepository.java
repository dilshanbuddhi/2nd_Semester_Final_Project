package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.Guid;
import org.example.smallworld_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuidRepository extends JpaRepository<Guid, Long> {
    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    Object findByUser(User user);

}
