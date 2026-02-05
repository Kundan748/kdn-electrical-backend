package com.kdn.kdnelectrical.service;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.kdn.kdnelectrical.entity.Invoice;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class InvoicePdfService {

    public String generatePdf(Invoice invoice) {

        try {

            String filePath = "invoices/invoice_" + invoice.getId() + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            // Title
            document.add(new Paragraph("KDN ELECTRICAL SERVICES"));
            document.add(new Paragraph("Customer Care: 7992288997 / 9128086552"));
            document.add(new Paragraph("Email: info@kdnelectrical"));
            document.add(new Paragraph("--------------------------------------------------"));

            // Invoice details
            document.add(new Paragraph("Invoice ID: " + invoice.getId()));
            document.add(new Paragraph("Booking ID: " + invoice.getBookingId()));
            document.add(new Paragraph("Service Charge: ₹" + invoice.getServiceCharge()));
            document.add(new Paragraph("Material Total: ₹" + invoice.getMaterialTotal()));
            document.add(new Paragraph("Grand Total: ₹" + invoice.getGrandTotal()));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                "Warranty: 1 Year Service Warranty, 5 Years Product Warranty.\nWarranty void if third party modifies work."
            ));

            document.close();

            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed");
        }
    }
}
