package org.example.smallworld_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long booking_id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Room room;
    private String check_in_date;
    private String  check_out_date;
    private int num_rooms;
    private float total_price;
    private String status;
    private String booking_date;

    public Booking(User user, Room room, String check_in_date, String check_out_date, int num_rooms, float total_price, String status, String booking_date) {
        this.user = user;
        this.room = room;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.num_rooms = num_rooms;
        this.total_price = total_price;
        this.status = status;
        this.booking_date = booking_date;
    }
}
