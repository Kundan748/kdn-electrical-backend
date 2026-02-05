package com.kdn.kdnelectrical.dto;

import java.math.BigDecimal;

public class AdminDashboardResponse {

    private BigDecimal totalRevenue;
    private long totalBookings;
    private long completedBookings;
    private long activeWorkers;
    private long todayBookings;

    // getters & setters

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }

    public long getTotalBookings() { return totalBookings; }
    public void setTotalBookings(long totalBookings) { this.totalBookings = totalBookings; }

    public long getCompletedBookings() { return completedBookings; }
    public void setCompletedBookings(long completedBookings) { this.completedBookings = completedBookings; }

    public long getActiveWorkers() { return activeWorkers; }
    public void setActiveWorkers(long activeWorkers) { this.activeWorkers = activeWorkers; }

    public long getTodayBookings() { return todayBookings; }
    public void setTodayBookings(long todayBookings) { this.todayBookings = todayBookings; }
}
