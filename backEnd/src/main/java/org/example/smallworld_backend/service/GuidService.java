package org.example.smallworld_backend.service;

import org.example.smallworld_backend.dto.GuidDTO;
import org.example.smallworld_backend.entity.User;

public interface GuidService {
    int saveGuid(GuidDTO guidDTO, User user);

    int updateGuid(GuidDTO guidDTO);

    int deleteGuid(String email);

    Object getAllGuids();

    String getEmailbyId(String guideId);


    GuidDTO getguidByUser(User user);
}
