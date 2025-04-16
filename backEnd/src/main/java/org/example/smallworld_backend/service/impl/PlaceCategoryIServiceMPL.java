package org.example.smallworld_backend.service.impl;


import org.example.smallworld_backend.dto.PlaceCategoryDTO;
import org.example.smallworld_backend.entity.PlaceCategory;
import org.example.smallworld_backend.repo.PlaceCategoryRepository;
import org.example.smallworld_backend.service.PlaceCategoryService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceCategoryIServiceMPL implements PlaceCategoryService {
    @Autowired
    private PlaceCategoryRepository placeCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public int savePlaceCategory(PlaceCategoryDTO placeCt) {
        if (placeCategoryRepository.existsPlaceCategoriesByName(placeCt.getName())) {
            return VarList.Not_Acceptable;
        }else {
            placeCategoryRepository.save(modelMapper.map(placeCt, PlaceCategory.class));
            return VarList.Created;
        }
    }

    @Override
    public int deletePlaceCategory(String id) {
       if (placeCategoryRepository.existsById(Long.valueOf(id))) {
           placeCategoryRepository.deleteById(String.valueOf(Long.valueOf(id)));
           return VarList.OK;
       }else {
           return VarList.Bad_Gateway;
       }
    }

    @Override
    public int updatePlaceCategory(PlaceCategoryDTO placeCt) {
        if (placeCategoryRepository.existsById(placeCt.getId())) {
            System.out.println(placeCt.getId() + " " + placeCt.getName());
            placeCategoryRepository.update( placeCt.getName(),placeCt.getDescription(), placeCt.getId());
            return VarList.OK;
        }else {
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public Object getAllPlaceCategory() {
        try {
            return placeCategoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
