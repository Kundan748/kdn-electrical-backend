package com.kdn.kdnelectrical.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.kdn.kdnelectrical.dto.AdminDashboardResponse;
import com.kdn.kdnelectrical.entity.BookingStatus;
import com.kdn.kdnelectrical.entity.User.Role;
import com.kdn.kdnelectrical.repository.BookingRepository;
import com.kdn.kdnelectrical.repository.InvoiceRepository;
import com.kdn.kdnelectrical.repository.UserRepository;

@Service
public class AdminDashboardService {

    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public AdminDashboardService(InvoiceRepository invoiceRepository,
                                 BookingRepository bookingRepository,
                                 UserRepository userRepository) {

        this.invoiceRepository = invoiceRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public AdminDashboardResponse getDashboardData() {

        AdminDashboardResponse res = new AdminDashboardResponse();

        res.setTotalRevenue(invoiceRepository.getTotalRevenue());

        res.setTotalBookings(bookingRepository.count());

        res.setCompletedBookings(
                bookingRepository.countByStatus(BookingStatus.completed)
        );

        res.setActiveWorkers(
                userRepository.countByRole(Role.WORKER)
        );

        res.setTodayBookings(
                bookingRepository.countByBookingDate(LocalDate.now())
        );

        return res;
    }
}
