package com.kdn.kdnelectrical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.Warranty;

public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

    Optional<Warranty> findByInvoiceId(Integer invoiceId);
}
