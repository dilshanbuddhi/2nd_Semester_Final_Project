package org.example.smallworld_backend.service;

import org.example.smallworld_backend.dto.PlaceDTO;

public interface PlaceService {
    int savePlace(PlaceDTO place);

    int deletePlace(Long placeID);

    int updatePlace(PlaceDTO place);

    Object getAllPlace();

    Object getPlace(String id);

    Object getAllPlaceByCity(String city);
}
