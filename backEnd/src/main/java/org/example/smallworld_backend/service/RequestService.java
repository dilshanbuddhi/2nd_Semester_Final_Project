package org.example.smallworld_backend.service;

import org.example.smallworld_backend.dto.RequestDTO;

public interface RequestService {
    int saveRequest(RequestDTO requestDTO);

    Object getAllRequests(String id);

    int updateRequest(String id, String status);
}
