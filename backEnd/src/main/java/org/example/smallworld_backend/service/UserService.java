package org.example.smallworld_backend.service;


import org.example.smallworld_backend.dto.UserDTO;
import org.example.smallworld_backend.entity.User;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    int deleteUser(String email);

    User searchUserByEmail(String email);

    int resetPass(UserDTO exuser);

    Object getAllUser();

    int updateUser(UserDTO userDTO);
}