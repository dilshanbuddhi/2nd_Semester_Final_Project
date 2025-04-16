package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.UserDTO;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.repo.UserRepository;
import org.example.smallworld_backend.service.UserService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user=userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int deleteUser(String email) {
        try {
            if (userRepository.existsByEmail(email)){
                User user = userRepository.findByEmail(email);
                userRepository.deleteByEmail(email);
                return VarList.OK;
                //throw new RuntimeException("User deleted");
            }else {
                return VarList.Not_Found;
                //throw new RuntimeException("User not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public User searchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public int resetPass(UserDTO exuser) {
        if (!userRepository.existsByEmail(exuser.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            exuser.setPassword(passwordEncoder.encode(exuser.getPassword()));
            userRepository.updatepassword(exuser.getFirstName(),exuser.getPassword());
            return VarList.Created;
        }
    }

    @Override
    public Object getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public int updateUser(UserDTO userDTO) {
        try {
            userRepository.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getCountry(), userDTO.getEmail(), userDTO.getRole(), userDTO.getUid());
            return VarList.OK;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }

    public boolean ifEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
};
