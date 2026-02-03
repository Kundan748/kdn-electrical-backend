package com.kdn.kdnelectrical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.WorkCompletion;

public interface WorkCompletionRepository
        extends JpaRepository<WorkCompletion, Integer> {

    Optional<WorkCompletion> findByBookingId(Integer bookingId);
}
