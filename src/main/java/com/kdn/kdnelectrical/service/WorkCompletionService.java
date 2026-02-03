package com.kdn.kdnelectrical.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdn.kdnelectrical.dto.WorkCompletionRequest;
import com.kdn.kdnelectrical.entity.*;
import com.kdn.kdnelectrical.repository.*;

@Service
public class WorkCompletionService {

    private final WorkCompletionRepository completionRepository;
    private final BookingRepository bookingRepository;
    private final BookingWorkerRepository bookingWorkerRepository;
    private final UserRepository userRepository;

    public WorkCompletionService(
            WorkCompletionRepository completionRepository,
            BookingRepository bookingRepository,
            BookingWorkerRepository bookingWorkerRepository,
            UserRepository userRepository) {
        this.completionRepository = completionRepository;
        this.bookingRepository = bookingRepository;
        this.bookingWorkerRepository = bookingWorkerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void completeWork(String phone, Integer bookingId,
                             WorkCompletionRequest request) {

        // 1️⃣ Worker validation
        User worker = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        // 2️⃣ Booking validation
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 3️⃣ Check assignment
        BookingWorker bw = bookingWorkerRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not assigned"));

        if (!bw.getWorkerId().equals(worker.getId())) {
            throw new RuntimeException("Booking not assigned to this worker");
        }

        // 4️⃣ Prevent double completion
        completionRepository.findByBookingId(bookingId)
                .ifPresent(c -> {
                    throw new RuntimeException("Work already completed");
                });

        // 5️⃣ Save completion
        WorkCompletion completion = new WorkCompletion();
        completion.setBookingId(bookingId);
        completion.setBeforeImageUrl(request.getBeforeImageUrl());
        completion.setAfterImageUrl(request.getAfterImageUrl());
        completion.setWorkNotes(request.getWorkNotes());
        completionRepository.save(completion);

        // 6️⃣ Update booking status
        booking.setStatus(BookingStatus.completed);
        bookingRepository.save(booking);
    }
}
