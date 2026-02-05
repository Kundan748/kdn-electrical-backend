package com.kdn.kdnelectrical.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	long countByStatus(com.kdn.kdnelectrical.entity.BookingStatus status);

	long countByBookingDate(LocalDate date);

	
    List<Booking> findByCustomerId(Long customerId);

	Optional<Booking> findById(Integer bookingId);
}
