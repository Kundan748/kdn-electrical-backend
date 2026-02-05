package com.kdn.kdnelectrical.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.entity.Invoice;
import com.kdn.kdnelectrical.repository.InvoiceRepository;
import com.kdn.kdnelectrical.service.FileDownloadService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceDownloadController {

    private final InvoiceRepository invoiceRepository;
    private final FileDownloadService fileService;

    public InvoiceDownloadController(InvoiceRepository invoiceRepository,
                                     FileDownloadService fileService) {
        this.invoiceRepository = invoiceRepository;
        this.fileService = fileService;
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<FileSystemResource> downloadInvoice(@PathVariable Integer id,
                                                              Authentication auth) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        FileSystemResource resource =
                fileService.getInvoiceFile(invoice.getInvoicePdfUrl());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=invoice_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
