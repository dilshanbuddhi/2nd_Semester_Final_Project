package org.example.smallworld_backend.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.example.smallworld_backend.dto.BookingDTO;
import org.example.smallworld_backend.entity.Booking;
import org.example.smallworld_backend.entity.BookingPayment;
import org.example.smallworld_backend.entity.Room;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.repo.BookingPaymentRepository;
import org.example.smallworld_backend.repo.BookingRepository;
import org.example.smallworld_backend.repo.RoomRepository;
import org.example.smallworld_backend.repo.UserRepository;
import org.example.smallworld_backend.service.BookingService;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingServiceIMPL implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingPaymentRepository bookingPaymentRepository;

    @Override
    @Transactional
    public int saveBooking(BookingDTO bookingDTO, HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");
            User user = userRepository.findByEmail(email);
            System.out.println(user.getUid() + " service eken gaththeeee");
            Optional<Room> room = Optional.of(roomRepository.findById((long) bookingDTO.getRoom_id()).get());

            System.out.println(room.get().getRoom_type().getAmenities() + " hyuiopohcfhihfxcgi");

            Booking booking = new Booking(
                    user,
                    room.get(),
                    bookingDTO.getCheck_in_date(),
                    bookingDTO.getCheck_out_date(),
                    bookingDTO.getNum_rooms(),
                    bookingDTO.getTotal_price(),
                    bookingDTO.getStatus(),
                    bookingDTO.getBooking_date()
            );

            String date = String.valueOf(LocalDate.now());

            BookingPayment bookingPayment = new BookingPayment(
              booking,
              bookingDTO.getPaymentMethod(),
              bookingDTO.getStatus(),
                    date
            );

            //save data for table
            bookingRepository.save(booking);
            bookingPaymentRepository.save(bookingPayment);

            //and reduse karnn one room set eka
            roomRepository.updateRoomsQTY(bookingDTO.getNum_rooms() , bookingDTO.getRoom_id());

            return VarList.Created;
        }catch (Exception e) {
            e.printStackTrace();
            return VarList.Bad_Gateway;
        }
    }
}
