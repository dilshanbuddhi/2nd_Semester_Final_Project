package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.dto.GuidDTO;
import org.example.smallworld_backend.entity.Guid;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.repo.GuidRepository;
import org.example.smallworld_backend.repo.UserRepository;
import org.example.smallworld_backend.service.GuidService;
import org.example.smallworld_backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuidServiceIMPL implements GuidService {
    @Autowired
    private GuidRepository guidRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public int saveGuid(GuidDTO guidDTO, User user) {
        System.out.println("mekata awaaa");

        try {
            System.out.println("into try");
            if(guidRepository.existsByEmail(guidDTO.getEmail())){
                System.out.println("into if");
                return VarList.Not_Acceptable;
            }
            else {
                Guid guid = modelMapper.map(guidDTO, Guid.class);
                guid.setUser(user);

                System.out.println(guid.getExperience_of_years());
                guid.setExperience_of_years((int) guidDTO.getYears_experience());
                System.out.println(guid.getExperience_of_years());
                //guid.setCertifications(guidDTO.getCertifications());
                guidRepository.save(guid);
                return VarList.OK;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateGuid(GuidDTO guidDTO) {
        try {
            Guid guid = modelMapper.map(guidDTO, Guid.class);
            guid.setUser(userRepository.findByEmail("dilshan@gmail.com"));
            guid.setCertifications(guidDTO.getCertifications());
            guidRepository.save(guid);
            return VarList.OK;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int deleteGuid(String email) {
        try {
           // Guid guid = guidRepository.findByEmail(email);
            guidRepository.deleteByEmail(email);
            return VarList.OK;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Object getAllGuids() {
        try {
            return guidRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getEmailbyId(String guideId) {
        try {
            return guidRepository.findById(Long.valueOf(guideId)).get().getEmail();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GuidDTO getguidByUser(User user) {
        try {
            Guid guid = (Guid) guidRepository.findByUser(user);
            GuidDTO dto = modelMapper.map(guid, GuidDTO.class);
            dto.setYears_experience(guid.getExperience_of_years());
            return dto;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
