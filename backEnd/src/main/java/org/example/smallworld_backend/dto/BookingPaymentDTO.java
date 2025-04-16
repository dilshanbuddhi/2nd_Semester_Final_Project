package org.example.smallworld_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingPaymentDTO {
    private long paymentId;
    private long bookingId;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentDate;
}
