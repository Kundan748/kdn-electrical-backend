package com.kdn.kdnelectrical.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_workers")
public class BookingWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_id", nullable = false, unique = true)
    private Integer bookingId;

    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "assigned_at", insertable = false, updatable = false)
    private LocalDateTime assignedAt;

    // getters & setters
    public Integer getId() { return id; }

    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }

    public Long getWorkerId() { return workerId; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
}
