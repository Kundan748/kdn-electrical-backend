package com.kdn.kdnelectrical.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;

import com.kdn.kdnelectrical.entity.ServiceType;

public class CreateBookingRequest {

    private ServiceType serviceType;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public LocalTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalTime bookingTime) { this.bookingTime = bookingTime; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
}
