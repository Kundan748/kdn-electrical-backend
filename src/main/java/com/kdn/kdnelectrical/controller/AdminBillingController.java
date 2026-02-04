package com.kdn.kdnelectrical.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.kdnelectrical.dto.CreateInvoiceRequest;
import com.kdn.kdnelectrical.entity.Invoice;
import com.kdn.kdnelectrical.service.AdminBillingService;

@RestController
@RequestMapping("/api/admin/invoices")
public class AdminBillingController {

    private final AdminBillingService billingService;

    public AdminBillingController(AdminBillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Invoice createInvoice(@PathVariable Integer bookingId,
                                 @RequestBody CreateInvoiceRequest request) {

        return billingService.createInvoice(bookingId, request);
    }
}
