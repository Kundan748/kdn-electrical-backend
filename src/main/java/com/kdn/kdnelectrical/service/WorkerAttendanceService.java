package com.kdn.kdnelectrical.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdn.kdnelectrical.dto.ClockInRequest;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.entity.WorkerAttendance;
import com.kdn.kdnelectrical.repository.UserRepository;
import com.kdn.kdnelectrical.repository.WorkerAttendanceRepository;

@Service
public class WorkerAttendanceService {

    private final WorkerAttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public WorkerAttendanceService(WorkerAttendanceRepository attendanceRepository,
                                   UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public WorkerAttendance clockIn(String phone, ClockInRequest request) {

        User worker = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        LocalDate today = LocalDate.now();

        attendanceRepository.findByWorkerIdAndDate(worker.getId(), today)
                .ifPresent(a -> {
                    throw new RuntimeException("Already clocked in today");
                });

        WorkerAttendance attendance = new WorkerAttendance();
        attendance.setWorkerId(worker.getId());
        attendance.setDate(today);
        attendance.setClockIn(LocalTime.now());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());

        return attendanceRepository.save(attendance);
    }

    @Transactional
    public WorkerAttendance clockOut(String phone) {

        User worker = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        WorkerAttendance attendance = attendanceRepository
                .findByWorkerIdAndDate(worker.getId(), LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Clock-in not found"));

        if (attendance.getClockOut() != null) {
            throw new RuntimeException("Already clocked out");
        }

        attendance.setClockOut(LocalTime.now());
        return attendanceRepository.save(attendance);
    }
}
