package org.example.smallworld_backend.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.smallworld_backend.dto.BookingDTO;

public interface BookingService {
    int saveBooking(BookingDTO bookingDTO , HttpServletRequest request);
}
