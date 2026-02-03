package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.WorkCompletionRequest;
import com.kdn.kdnelectrical.service.WorkCompletionService;

@RestController
@RequestMapping("/api/worker/bookings")
public class WorkCompletionController {

    private final WorkCompletionService completionService;

    public WorkCompletionController(WorkCompletionService completionService) {
        this.completionService = completionService;
    }

    @PostMapping("/{bookingId}/complete")
    @PreAuthorize("hasRole('WORKER')")
    public String completeWork(@PathVariable Integer bookingId,
                               Authentication authentication,
                               @RequestBody WorkCompletionRequest request) {

        completionService.completeWork(authentication.getName(), bookingId, request);
        return "Work completed successfully";
    }
}
