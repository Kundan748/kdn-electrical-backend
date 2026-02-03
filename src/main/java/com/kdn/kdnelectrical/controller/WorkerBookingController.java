package com.kdn.kdnelectrical.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.entity.BookingWorker;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.repository.BookingWorkerRepository;
import com.kdn.kdnelectrical.repository.UserRepository;

@RestController
@RequestMapping("/api/worker/bookings")
public class WorkerBookingController {

    private final BookingWorkerRepository bookingWorkerRepository;
    private final UserRepository userRepository;

    public WorkerBookingController(BookingWorkerRepository bookingWorkerRepository,
                                   UserRepository userRepository) {
        this.bookingWorkerRepository = bookingWorkerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('WORKER')")
    public List<BookingWorker> myAssignedBookings(Authentication authentication) {

        String phone = authentication.getName();
        User worker = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        return bookingWorkerRepository.findByWorkerId(worker.getId());
    }
}
