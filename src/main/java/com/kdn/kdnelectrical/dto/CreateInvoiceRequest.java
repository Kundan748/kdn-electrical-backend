package com.kdn.kdnelectrical.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreateInvoiceRequest {

    private BigDecimal serviceCharge;
    private List<MaterialItem> materials;

    public static class MaterialItem {
        public Integer materialId;
        public Integer quantity;
    }

    // getters setters
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public List<MaterialItem> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialItem> materials) {
		this.materials = materials;
	}

}
