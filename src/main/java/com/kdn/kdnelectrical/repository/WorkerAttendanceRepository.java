package com.kdn.kdnelectrical.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.WorkerAttendance;

public interface WorkerAttendanceRepository
        extends JpaRepository<WorkerAttendance, Integer> {

    Optional<WorkerAttendance> findByWorkerIdAndDate(Long workerId, LocalDate date);
}
