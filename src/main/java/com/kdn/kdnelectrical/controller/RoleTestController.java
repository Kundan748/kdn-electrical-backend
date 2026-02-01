package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "ADMIN ACCESS OK";
    }

    @GetMapping("/api/worker")
    @PreAuthorize("hasRole('WORKER')")
    public String workerOnly() {
        return "WORKER ACCESS OK";
    }

    @GetMapping("/api/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerOnly() {
        return "CUSTOMER ACCESS OK";
    }
}
