package org.example.smallworld_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private int booking_id;
    private String user_id;
    private int room_id;
    private String check_in_date;
    private String  check_out_date;
    private int num_rooms;
    private float total_price;
    private String status;
    private String booking_date;
    private String paymentMethod;

    public BookingDTO(int room_id, String check_in_date, String check_out_date, int num_rooms, float total_price, String status, String booking_date, String paymentMethod) {
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.num_rooms = num_rooms;
        this.total_price = total_price;
        this.status = status;
        this.booking_date = booking_date;
        this.paymentMethod = paymentMethod;
    }
}
