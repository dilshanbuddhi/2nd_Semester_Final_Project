package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, String> {
    boolean existsPlaceCategoriesByName(String name);

    void deleteByName(String name);

    PlaceCategory findByName(String name);

    boolean existsById(Long id);

    @Modifying
    @Query("UPDATE PlaceCategory p SET p.name = ?1, p.description = ?2 WHERE p.id = ?3")
    void update(String name, String description, Long id);
}
