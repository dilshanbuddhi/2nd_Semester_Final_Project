package org.example.smallworld_backend.controller;

import org.example.smallworld_backend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/request")
@CrossOrigin
public class RequestController {

    @Autowired
    private RequestService requestService;

}
