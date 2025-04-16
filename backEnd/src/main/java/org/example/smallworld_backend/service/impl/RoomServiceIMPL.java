package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.RoomDTO;
import org.example.smallworld_backend.entity.Room;
import org.example.smallworld_backend.repo.HotelRepository;
import org.example.smallworld_backend.repo.RoomRepository;
import org.example.smallworld_backend.repo.RoomTypeRepository;
import org.example.smallworld_backend.service.RoomService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceIMPL implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public int saveRoom(RoomDTO roomDTO) {
        try {
            System.out.println(roomDTO.getRoom_type_Id() + "   " + roomDTO.getHotel_id());
            Room room = new Room(
                   /* hotelRepository.getReferenceById(roomDTO.getHotelId()),
                    roomTypeRepository.getReferenceById(roomDTO.getRoom_type_Id()),*/
                    hotelRepository.findById(roomDTO.getHotel_id()).get(),
                    roomTypeRepository.findById(roomDTO.getRoom_type_Id()).get(),
                    roomDTO.getPrice(),
                    roomDTO.getTotalRooms(),
                    roomDTO.getAvailableRooms()
            );
            System.out.println(room);
            roomRepository.save(room);
            return VarList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
        }

    @Override
    public int deleteRoom(Long roomID) {
        try {
            if (!roomRepository.existsById(roomID)) {
                return VarList.Bad_Request;
            }
            roomRepository.deleteById(Long.valueOf(roomID));
            return VarList.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public int updateRoom(RoomDTO roomDTO) {
        try {
            if (!roomRepository.existsById(roomDTO.getId())) {
                return VarList.Bad_Request;
            }
            Room room = new Room(
                   /* hotelRepository.getReferenceById(roomDTO.getHotelId()),
                    roomTypeRepository.getReferenceById(roomDTO.getRoom_type_Id()),*/
                    hotelRepository.findById(roomDTO.getHotel_id()).get(),
                    roomTypeRepository.findById(roomDTO.getRoom_type_Id()).get(),
                    roomDTO.getPrice(),
                    roomDTO.getTotalRooms(),
                    roomDTO.getAvailableRooms()
            );
            roomRepository.save(room);
            return VarList.OK;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
    }
    }

    @Override
    public Object getAllRoom() {
        try {
            return roomRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getRoom(Long hotelId) {
        try {
            return (List<Room>) roomRepository.findAllByHotelId(hotelId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
