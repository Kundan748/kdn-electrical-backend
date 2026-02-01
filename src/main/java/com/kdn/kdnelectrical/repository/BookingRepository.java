package com.kdn.kdnelectrical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);
}
