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
public class GuidDTO {
    private long id;
    private String user_id;
    private String profile_image;
    private String name;
    private String email;
    private String phone_number;
    private String address;
    private String about_me;
    private List<String> languages;
    private int years_experience;
    private List<String> certifications;
    private String price;
    private String availability;


    public GuidDTO(String profile_image, String name, String email, String phone_number, String address, String about_me, List<String> languages, int years_experience, List<String> certifications, String price, String availability ) {
        this.profile_image = profile_image;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.about_me = about_me;
        this.languages = languages;
        this.years_experience = years_experience;
        this.certifications = certifications;
        this.price = price;
        this.availability = availability;
    }
}
