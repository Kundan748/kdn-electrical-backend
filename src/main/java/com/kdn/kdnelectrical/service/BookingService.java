package com.kdn.kdnelectrical.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdn.kdnelectrical.dto.CreateBookingRequest;
import com.kdn.kdnelectrical.entity.Booking;
import com.kdn.kdnelectrical.entity.BookingStatus;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.repository.BookingRepository;
import com.kdn.kdnelectrical.repository.UserRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(String phone, CreateBookingRequest request) {

        // 🔑 Convert phone → user
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setCustomerId(user.getId()); // ✅ CORRECT ID
        booking.setServiceType(request.getServiceType());
        booking.setBookingDate(request.getBookingDate());
        booking.setBookingTime(request.getBookingTime());
        booking.setAddress(request.getAddress());
        booking.setLatitude(request.getLatitude());
        booking.setLongitude(request.getLongitude());
        booking.setStatus(BookingStatus.pending);

        return bookingRepository.save(booking);
    }

    public List<Booking> getCustomerBookings(String phone) {

        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByCustomerId(user.getId());
    }
}
