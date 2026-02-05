package com.kdn.kdnelectrical.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdn.kdnelectrical.dto.CreateInvoiceRequest;
import com.kdn.kdnelectrical.entity.*;
import com.kdn.kdnelectrical.repository.*;

@Service
public class AdminBillingService {

    private final InvoiceRepository invoiceRepo;
    private final BillingMaterialRepository billingRepo;
    private final MaterialRepository materialRepo;
    private final WarrantyRepository warrantyRepo;
    private final InvoicePdfService pdfService;

    public AdminBillingService(InvoiceRepository invoiceRepo,
            BillingMaterialRepository billingRepo,
            MaterialRepository materialRepo,
            WarrantyRepository warrantyRepo,
            InvoicePdfService pdfService) {

this.invoiceRepo = invoiceRepo;
this.billingRepo = billingRepo;
this.materialRepo = materialRepo;
this.warrantyRepo = warrantyRepo;
this.pdfService = pdfService;
}


    @Transactional
    public Invoice createInvoice(Integer bookingId,
                                 CreateInvoiceRequest request) {

        BigDecimal materialTotal = BigDecimal.ZERO;

        // 1️⃣ Save materials
        for (CreateInvoiceRequest.MaterialItem item : request.getMaterials()) {

            Material material = materialRepo.findById(item.materialId)
                    .orElseThrow(() -> new RuntimeException("Material not found"));

            BigDecimal total = material.getUnitPrice()
                    .multiply(BigDecimal.valueOf(item.quantity));

            BillingMaterial bm = new BillingMaterial();
            bm.setBookingId(bookingId);
            bm.setMaterialId(item.materialId);
            bm.setQuantity(item.quantity);
            bm.setTotalPrice(total);
            billingRepo.save(bm);

            materialTotal = materialTotal.add(total);
        }

        // 2️⃣ Create invoice
        Invoice invoice = new Invoice();
        invoice.setBookingId(bookingId);
        invoice.setServiceCharge(request.getServiceCharge());
        invoice.setMaterialTotal(materialTotal);

        BigDecimal grand = request.getServiceCharge().add(materialTotal);
        invoice.setGrandTotal(grand);

        invoiceRepo.save(invoice);


        // 3️⃣ Create warranty automatically
        Warranty warranty = new Warranty();
        warranty.setInvoiceId(invoice.getId());
        warranty.setServiceWarrantyEnd(LocalDate.now().plusYears(1));
        warranty.setProductWarrantyEnd(LocalDate.now().plusYears(5));
        warranty.setTerms(
            "1 Year Service Warranty, 5 Years Product Warranty. Warranty void if third party modifies work."
        );

        warrantyRepo.save(warranty);
        
     // ✅ Generate PDF
        String pdfUrl = pdfService.generatePdf(invoice);
        invoice.setInvoicePdfUrl(pdfUrl);

        invoiceRepo.save(invoice);

        return invoice;
    }
}
