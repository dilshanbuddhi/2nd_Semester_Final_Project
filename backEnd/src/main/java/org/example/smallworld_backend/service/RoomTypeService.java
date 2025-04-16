package org.example.smallworld_backend.service;

import org.example.smallworld_backend.dto.RoomTypeDTO;

public interface RoomTypeService {
    int saveRoomType(RoomTypeDTO roomTypeDTO);

    int updateRoomType(RoomTypeDTO roomTypeDTO);

    int deleteRoomType(Long roomTypeID);

    Object getAllRoomType() ;
}
