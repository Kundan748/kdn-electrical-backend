package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.ClockInRequest;
import com.kdn.kdnelectrical.entity.WorkerAttendance;
import com.kdn.kdnelectrical.service.WorkerAttendanceService;

@RestController
@RequestMapping("/api/worker/attendance")
public class WorkerAttendanceController {

    private final WorkerAttendanceService attendanceService;

    public WorkerAttendanceController(WorkerAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/clock-in")
    @PreAuthorize("hasRole('WORKER')")
    public WorkerAttendance clockIn(Authentication authentication,
                                    @RequestBody ClockInRequest request) {

        return attendanceService.clockIn(authentication.getName(), request);
    }

    @PostMapping("/clock-out")
    @PreAuthorize("hasRole('WORKER')")
    public WorkerAttendance clockOut(Authentication authentication) {

        return attendanceService.clockOut(authentication.getName());
    }
}
