package org.example.smallworld_backend.controller;

import org.example.smallworld_backend.service.BookingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking-payment")
@CrossOrigin
public class BookingPaymentController {
    @Autowired
    private BookingPaymentService bookingPaymentService;
}
