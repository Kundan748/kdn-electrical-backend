package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.AdminDashboardResponse;
import com.kdn.kdnelectrical.service.AdminDashboardService;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    public AdminDashboardController(AdminDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDashboardResponse getDashboard() {
        return dashboardService.getDashboardData();
    }
}
