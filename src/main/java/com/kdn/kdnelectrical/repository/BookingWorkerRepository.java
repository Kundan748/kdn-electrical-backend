package com.kdn.kdnelectrical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.BookingWorker;

public interface BookingWorkerRepository extends JpaRepository<BookingWorker, Integer> {

    Optional<BookingWorker> findByBookingId(Integer bookingId);

    List<BookingWorker> findByWorkerId(Long workerId);
}
