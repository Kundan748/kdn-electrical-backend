package com.kdn.kdnelectrical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findByBookingId(Integer bookingId);
}
