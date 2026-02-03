package com.kdn.kdnelectrical.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdn.kdnelectrical.entity.Booking;
import com.kdn.kdnelectrical.entity.BookingStatus;
import com.kdn.kdnelectrical.entity.BookingWorker;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.repository.BookingRepository;
import com.kdn.kdnelectrical.repository.BookingWorkerRepository;
import com.kdn.kdnelectrical.repository.UserRepository;

@Service
public class BookingAssignmentService {

    private final BookingRepository bookingRepository;
    private final BookingWorkerRepository bookingWorkerRepository;
    private final UserRepository userRepository;

    public BookingAssignmentService(
            BookingRepository bookingRepository,
            BookingWorkerRepository bookingWorkerRepository,
            UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingWorkerRepository = bookingWorkerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void assignWorker(Integer bookingId, Long workerId) {

        // 1️⃣ Validate booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2️⃣ Validate worker
        User worker = userRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        if (!worker.getRole().name().equals("WORKER")) {
            throw new RuntimeException("User is not a worker");
        }

        // 3️⃣ Ensure booking not already assigned
        bookingWorkerRepository.findByBookingId(bookingId)
                .ifPresent(bw -> {
                    throw new RuntimeException("Booking already assigned");
                });

        // 4️⃣ Save assignment
        BookingWorker bw = new BookingWorker();
        bw.setBookingId(bookingId);
        bw.setWorkerId(workerId);
        bookingWorkerRepository.save(bw);

        // 5️⃣ Update booking status
        booking.setStatus(BookingStatus.assigned);
        bookingRepository.save(booking);
    }
}
