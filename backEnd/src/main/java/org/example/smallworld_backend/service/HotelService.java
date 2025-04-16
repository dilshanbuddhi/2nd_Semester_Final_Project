package org.example.smallworld_backend.service;

import org.apache.catalina.LifecycleState;
import org.example.smallworld_backend.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    int saveHotel(HotelDTO hotelDTO);

    int updateHotel(HotelDTO hotel);

    Object getAllHotel();

    int deleteHotel(Long hotelID);

    List<Object> getAllHotelByCity(String city);
}
