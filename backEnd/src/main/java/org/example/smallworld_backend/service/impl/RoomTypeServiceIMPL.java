package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.RoomTypeDTO;
import org.example.smallworld_backend.entity.RoomType;
import org.example.smallworld_backend.repo.RoomTypeRepository;
import org.example.smallworld_backend.service.RoomTypeService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomTypeServiceIMPL implements RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveRoomType(RoomTypeDTO roomTypeDTO) {
        try{
         /*   if (roomTypeRepository.existsByRoom_type(roomTypeDTO.getRoom_type())){

                return VarList.Bad_Gateway;
            }*/
            roomTypeRepository.save(modelMapper.map(roomTypeDTO, RoomType.class));
            return VarList.OK;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int updateRoomType(RoomTypeDTO roomTypeDTO) {
        try{
            if (roomTypeRepository.existsById(roomTypeDTO.getId())){
                roomTypeRepository.save(modelMapper.map(roomTypeDTO, RoomType.class));
                return VarList.OK;
            }
            return VarList.Bad_Request;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int deleteRoomType(Long roomTypeID) {
        try{
            if (!roomTypeRepository.existsById(roomTypeID)){
                return VarList.Bad_Request;
            }
            roomTypeRepository.deleteById(Long.valueOf(roomTypeID));
            return VarList.OK;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Object getAllRoomType() {
        try {
            return roomTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
