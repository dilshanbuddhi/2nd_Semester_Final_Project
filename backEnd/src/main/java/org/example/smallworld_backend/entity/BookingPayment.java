package org.example.smallworld_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookingPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Booking booking;
    private String payment_method;
    private String payment_status;
    private String payment_date;

    public BookingPayment(Booking booking, String payment_method, String payment_status, String payment_date) {
        this.booking = booking;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
        this.payment_date = payment_date;
    }
}
