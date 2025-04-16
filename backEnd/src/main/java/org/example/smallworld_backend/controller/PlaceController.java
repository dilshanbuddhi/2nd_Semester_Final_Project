package org.example.smallworld_backend.controller;

import jakarta.annotation.security.PermitAll;
import org.example.smallworld_backend.dto.PlaceDTO;
import org.example.smallworld_backend.dto.ResponseDTO;
import org.example.smallworld_backend.service.PlaceService;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> savePlace(@RequestParam("name") String name,
                                                         @RequestParam("categoryID") String categoryID,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("city") String city,
                                                         @RequestParam("location") String location,
                                                         @RequestParam("latitude") String latitude,
                                                         @RequestParam("longitude") String longitude,
                                                         @RequestParam("imageFiles") List<
                                                                 String> imageFiles) {
        try {
            PlaceDTO place = new PlaceDTO();
            place.setName(name);
            place.setCategoryID(categoryID);
            place.setDescription(description);
            place.setLocation(location);
            place.setLatitude(latitude);
            place.setLongitude(longitude);
            place.setImage(imageFiles);
            place.setCity(city);

            int res = placeService.savePlace(place);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", null));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Place Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deletePlace(@RequestParam("placeID") Long placeID) {
        try {
            int res = placeService.deletePlace(placeID);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                    //dghvhfjh
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updatePlace(@RequestBody PlaceDTO place) {
        try {
            int res = placeService.updatePlace(place);
            switch (res) {
                case VarList.OK -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
                    }
    }

    @GetMapping("/getAll")
    @PermitAll
    public ResponseEntity<ResponseDTO> getAllPlace() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", placeService.getAllPlace()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/getOne/{id}")
    @PermitAll
    public ResponseEntity<ResponseDTO> getPlace(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", placeService.getPlace(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/getAllByCity/{city}")
    @PermitAll
    public ResponseEntity<ResponseDTO> getAllPlaceByCity(@PathVariable String city) {
        System.out.println(city + "      qqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", placeService.getAllPlaceByCity(city)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

}
