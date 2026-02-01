package com.kdn.kdnelectrical.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.CreateBookingRequest;
import com.kdn.kdnelectrical.entity.Booking;
import com.kdn.kdnelectrical.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
//CREATE
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Booking createBooking(Authentication authentication,
                                 @RequestBody CreateBookingRequest request) {

        String phone = authentication.getName(); // JWT subject
        return bookingService.createBooking(phone, request);
    }
//VIEW
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Booking> myBookings(Authentication authentication) {

        String phone = authentication.getName();
        return bookingService.getCustomerBookings(phone);
    }

}
