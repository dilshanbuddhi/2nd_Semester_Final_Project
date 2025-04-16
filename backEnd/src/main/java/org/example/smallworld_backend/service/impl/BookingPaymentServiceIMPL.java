package org.example.smallworld_backend.service.impl;

import org.example.smallworld_backend.repo.BookingPaymentRepository;
import org.example.smallworld_backend.service.BookingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingPaymentServiceIMPL implements BookingPaymentService {
    @Autowired
    private BookingPaymentRepository bookingPaymentRepository;
}
