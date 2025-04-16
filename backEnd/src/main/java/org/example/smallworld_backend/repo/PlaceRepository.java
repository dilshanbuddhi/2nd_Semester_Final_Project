package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Object findAllByCity(String city);

    List<Place> getAllByCity(String city);
}
