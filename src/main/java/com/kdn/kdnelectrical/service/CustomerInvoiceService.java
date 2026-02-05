package com.kdn.kdnelectrical.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kdn.kdnelectrical.entity.Booking;
import com.kdn.kdnelectrical.entity.Invoice;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.repository.BookingRepository;
import com.kdn.kdnelectrical.repository.InvoiceRepository;
import com.kdn.kdnelectrical.repository.UserRepository;

@Service
public class CustomerInvoiceService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final InvoiceRepository invoiceRepository;

    public CustomerInvoiceService(UserRepository userRepository,
                                  BookingRepository bookingRepository,
                                  InvoiceRepository invoiceRepository) {

        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getMyInvoices(String phone) {

        // 1️⃣ find user
        User customer = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ find bookings
        List<Booking> bookings =
                bookingRepository.findByCustomerId(customer.getId());

        // 3️⃣ extract booking IDs
        List<Integer> bookingIds =
                bookings.stream()
                        .map(Booking::getId)
                        .collect(Collectors.toList());

        if (bookingIds.isEmpty()) {
            return List.of();
        }

        // 4️⃣ find invoices
        return invoiceRepository.findByBookingIdIn(bookingIds);
    }
}
