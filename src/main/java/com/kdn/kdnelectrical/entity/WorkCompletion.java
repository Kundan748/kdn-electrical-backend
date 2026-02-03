package com.kdn.kdnelectrical.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "work_completion")
public class WorkCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_id", nullable = false)
    private Integer bookingId;

    @Column(name = "before_image_url", columnDefinition = "TEXT")
    private String beforeImageUrl;

    @Column(name = "after_image_url", columnDefinition = "TEXT")
    private String afterImageUrl;

    @Column(name = "work_notes", columnDefinition = "TEXT")
    private String workNotes;

    @Column(name = "completed_at", insertable = false, updatable = false)
    private LocalDateTime completedAt;

    // getters & setters
    public Integer getId() { return id; }

    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }

    public String getBeforeImageUrl() { return beforeImageUrl; }
    public void setBeforeImageUrl(String beforeImageUrl) { this.beforeImageUrl = beforeImageUrl; }

    public String getAfterImageUrl() { return afterImageUrl; }
    public void setAfterImageUrl(String afterImageUrl) { this.afterImageUrl = afterImageUrl; }

    public String getWorkNotes() { return workNotes; }
    public void setWorkNotes(String workNotes) { this.workNotes = workNotes; }

    public LocalDateTime getCompletedAt() { return completedAt; }
}
