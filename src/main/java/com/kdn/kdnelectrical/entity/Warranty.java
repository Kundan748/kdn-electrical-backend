package com.kdn.kdnelectrical.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "warranties")
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(nullable = false)
    private Integer invoiceId;
    private LocalDate serviceWarrantyEnd;
    private LocalDate productWarrantyEnd;

    @Column(columnDefinition = "TEXT")
    private String terms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDate getServiceWarrantyEnd() {
		return serviceWarrantyEnd;
	}

	public void setServiceWarrantyEnd(LocalDate serviceWarrantyEnd) {
		this.serviceWarrantyEnd = serviceWarrantyEnd;
	}

	public LocalDate getProductWarrantyEnd() {
		return productWarrantyEnd;
	}

	public void setProductWarrantyEnd(LocalDate productWarrantyEnd) {
		this.productWarrantyEnd = productWarrantyEnd;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

    
}
