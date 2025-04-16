package org.example.smallworld_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.smallworld_backend.dto.BookingDTO;
import org.example.smallworld_backend.dto.ResponseDTO;
import org.example.smallworld_backend.entity.Room;
import org.example.smallworld_backend.entity.User;
import org.example.smallworld_backend.service.BookingPaymentService;
import org.example.smallworld_backend.service.BookingService;
import org.example.smallworld_backend.service.RoomService;
import org.example.smallworld_backend.service.UserService;
import org.example.smallworld_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveBooking(@RequestBody BookingDTO bookingDTO , HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");
            User user = userService.searchUserByEmail(email);
            System.out.println(user.getUid()+ "  " + user.getRole() + "  " + user.getEmail());

            System.out.println(bookingDTO.getRoom_id()+ "  " + bookingDTO.getTotal_price() + "  " + bookingDTO.getPaymentMethod());


            int res = bookingService.saveBooking(bookingDTO,request);
            switch (res) {
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(VarList.OK, "Success", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
