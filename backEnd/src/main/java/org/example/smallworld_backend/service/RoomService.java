package org.example.smallworld_backend.service;

import org.example.smallworld_backend.dto.RoomDTO;
import org.example.smallworld_backend.entity.Room;

import java.util.List;

public interface RoomService {
    int saveRoom(RoomDTO roomDTO);

    int deleteRoom(Long roomID);

    int updateRoom(RoomDTO roomDTO);

    Object getAllRoom();

    List<Room> getRoom(Long hotelId);
}
