package org.example.smallworld_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private UUID uid;

    public UserDTO(String firstName, String lastName, String country, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private String firstName;
    private String lastName;
    private String country;
    private String email;
    private String password;
    private String role;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
