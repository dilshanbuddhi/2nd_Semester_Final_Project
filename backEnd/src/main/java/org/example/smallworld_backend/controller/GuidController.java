package org.example.smallworld_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.smallworld_backend.dto.GuidDTO;
import org.example.smallworld_backend.dto.ResponseDTO;
import org.example.smallworld_backend.dto.UserDTO;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.service.GuidService;
import org.example.smallworld_backend.service.impl.UserServiceImpl;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/guid")
@CrossOrigin
public class GuidController {
    @Autowired
    private GuidService guidService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveGuid(@RequestBody GuidDTO guidDTO, HttpServletRequest request) {
        System.out.println("saveGuid");
        //Getting the attributes set in the previous code

        String email = (String) request.getAttribute("email");
        String role = (String) request.getAttribute("role");

        System.out.println("Email: " + email);
        System.out.println("Role: " + role);

        System.out.println(guidDTO.getEmail());
        System.out.println(guidDTO.getYears_experience() + " year exp");

        User user = userService.searchUserByEmail(email);

        System.out.println(user.getUid() + user.getRole() + user.getEmail());


        try {
            // Create DTO and set the values

            int res = guidService.saveGuid(guidDTO , user);

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
            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateGuid(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone_number") String phoneNumber,
            @RequestParam("profile_photo") MultipartFile profilePhoto, // File upload
            @RequestParam("languages") List<String> languages, // List of languages
            @RequestParam("years_experience") int yearsExperience,
            @RequestParam("certifications") List<String> certifications) {

        System.out.println("saveGuid");


            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "ghjk", null));
        }


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteGuid(@RequestParam("email") String email) {
        try {
            int res = guidService.deleteGuid(email);
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
    public ResponseEntity<ResponseDTO> getAllGuids() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", guidService.getAllGuids()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseDTO> getOne(HttpServletRequest request){
        try {
            String email = (String) request.getAttribute("email");
            User user = userService.searchUserByEmail(email);

            GuidDTO guidDTO = guidService.getguidByUser(user);
            guidDTO.setUser_id(String.valueOf(user.getUid()));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                    VarList.OK, "Success", guidDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                    VarList.Bad_Gateway, "Error", null));

        }

    }

}
