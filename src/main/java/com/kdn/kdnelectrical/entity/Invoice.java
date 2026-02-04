package com.kdn.kdnelectrical.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "booking_id", unique = true,nullable = false)
    private Integer bookingId;

    private BigDecimal serviceCharge;
    private BigDecimal materialTotal;
    private BigDecimal grandTotal;

    @Column(columnDefinition = "TEXT")
    private String invoicePdfUrl;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
    
 // getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public BigDecimal getMaterialTotal() {
		return materialTotal;
	}

	public void setMaterialTotal(BigDecimal materialTotal) {
		this.materialTotal = materialTotal;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getInvoicePdfUrl() {
		return invoicePdfUrl;
	}

	public void setInvoicePdfUrl(String invoicePdfUrl) {
		this.invoicePdfUrl = invoicePdfUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
}
