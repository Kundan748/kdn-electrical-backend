package com.kdn.kdnelectrical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.kdnelectrical.entity.BillingMaterial;

public interface BillingMaterialRepository
        extends JpaRepository<BillingMaterial, Integer> {

    List<BillingMaterial> findByBookingId(Integer bookingId);
}
