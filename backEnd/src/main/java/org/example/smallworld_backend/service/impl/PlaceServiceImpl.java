package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.PlaceDTO;
import org.example.smallworld_backend.entity.Place;
import org.example.smallworld_backend.repo.PlaceCategoryRepository;
import org.example.smallworld_backend.repo.PlaceRepository;
import org.example.smallworld_backend.service.PlaceService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int savePlace(PlaceDTO place) {
        try {
           /* Optional<Place> existingPlace = placeRepository.findByNameAndLocation(place.getName(), place.getLocation());
            if (existingPlace.isPresent()) {
                return VarList.Not_Acceptable;
            }*/
//hj

            Place placeEntity = new Place(place.getName(), placeCategoryRepository.findById(place.getCategoryID()).get(), place.getDescription(),place.getCity(), place.getLocation(), place.getLatitude(), place.getLongitude(), place.getImage());

            // Save the place
            placeRepository.save(placeEntity);
            return VarList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public int deletePlace(Long placeID) {
        try {
            placeRepository.deleteById(Long.valueOf(placeID));
            return VarList.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public int updatePlace(PlaceDTO place) {
        try {
            placeRepository.save(modelMapper.map(place, Place.class));
            return VarList.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public Object getAllPlace() {
        try {
            return placeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getPlace(String id) {
        try {
            return placeRepository.findById(Long.valueOf(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getAllPlaceByCity(String city) {
        try {
            return placeRepository.getAllByCity(city);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}