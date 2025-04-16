package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {


    List<Room> findAllByHotelId(Long hotelId);

    Optional<Room> findById(Long id);

    @Modifying
    @Query(value = "UPDATE room r SET r.available_rooms = available_rooms - ?1 WHERE r.id = ?2", nativeQuery = true)
    void updateRoomsQTY(int numRooms, int roomId);

}
