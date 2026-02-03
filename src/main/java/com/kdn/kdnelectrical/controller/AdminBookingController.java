package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.service.BookingAssignmentService;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    private final BookingAssignmentService assignmentService;

    public AdminBookingController(BookingAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/{bookingId}/assign/{workerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String assignWorker(@PathVariable Integer bookingId,
                               @PathVariable Long workerId) {

        assignmentService.assignWorker(bookingId, workerId);
        return "Worker assigned successfully";
    }
}
