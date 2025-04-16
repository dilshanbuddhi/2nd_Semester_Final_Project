package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Object> findAllByCity(String city);
}
