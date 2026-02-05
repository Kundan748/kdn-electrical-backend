package com.kdn.kdnelectrical.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.entity.Invoice;
import com.kdn.kdnelectrical.service.CustomerInvoiceService;

@RestController
@RequestMapping("/api/customer/invoices")
public class CustomerInvoiceController {

    private final CustomerInvoiceService invoiceService;

    public CustomerInvoiceController(CustomerInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Invoice> myInvoices(Authentication authentication) {

        String phone = authentication.getName();
        return invoiceService.getMyInvoices(phone);
    }
}
