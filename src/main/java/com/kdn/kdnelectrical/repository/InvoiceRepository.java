package com.kdn.kdnelectrical.repository;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	@Query("SELECT COALESCE(SUM(i.grandTotal),0) FROM Invoice i")
	BigDecimal getTotalRevenue();

    Optional<Invoice> findByBookingId(Integer bookingId);
    List<Invoice> findByBookingIdIn(List<Integer> bookingIds);
}
