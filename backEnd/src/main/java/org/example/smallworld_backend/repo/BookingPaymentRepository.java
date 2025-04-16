package org.example.smallworld_backend.repo;

import org.example.smallworld_backend.entity.BookingPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingPaymentRepository extends JpaRepository<BookingPayment, Long> {

}
