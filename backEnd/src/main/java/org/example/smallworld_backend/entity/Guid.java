package org.example.smallworld_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Guid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    private String profile_image;
    private String name;
    private String email;
    private String phone_number;
    private String address;
    private String about_me;
    @ElementCollection
    @CollectionTable(name = "guid_languages", joinColumns = @JoinColumn(name = "guid_id"))
    @Column(name = "guid_languages")
    private List<String> languages;
    private int experience_of_years;
    @ElementCollection
    @CollectionTable(name = "certifications", joinColumns = @JoinColumn(name = "guid_id"))
    @Column(name = "guid_certifications")
    private List<String> certifications;
    private String price;
    private String availability;

    public Guid(String profile_image, String name, String email, String phone_number, String address, String about_me, List<String> languages, int experience_of_years, List<String> certifications, String price, String availability) {
        this.profile_image = profile_image;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.about_me = about_me;
        this.languages = languages;
        this.experience_of_years = experience_of_years;
        this.certifications = certifications;
        this.price = price;
        this.availability = availability;
    }

    public Guid(User user, String profile_image, String name, String email, String phone_number, String address, String about_me, List<String> languages, int experience_of_years, List<String> certifications, String availability) {
        this.user = user;
        this.profile_image = profile_image;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.about_me = about_me;
        this.languages = languages;
        this.experience_of_years = experience_of_years;
        this.certifications = certifications;
        this.availability = availability;
    }
}
