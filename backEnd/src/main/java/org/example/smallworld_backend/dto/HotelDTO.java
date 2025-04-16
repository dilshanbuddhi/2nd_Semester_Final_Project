package org.example.smallworld_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelDTO {
    private Long id;
    private String city;
    private String name;
    private String description;
    private String location;
    private String latitude;
    private String longitude;
    private List<String> amenities;
    private List<String> image;

    public HotelDTO(String city, String name, String description, String location, String latitude, String longitude, List<String> amenities, List<String> image) {
        this.city = city;
        this.name = name;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.amenities = amenities;
        this.image = image;
    }
}
