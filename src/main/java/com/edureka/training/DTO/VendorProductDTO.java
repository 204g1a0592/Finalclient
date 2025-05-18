package com.edureka.training.DTO;

import org.springframework.stereotype.Component;

import com.edureka.training.entity.Product;
import com.edureka.training.entity.Vendor;

@Component
public class VendorProductDTO {
	private Vendor vendor;
	private Product product;
	
	public VendorProductDTO() {}

	public VendorProductDTO(Vendor vendor, Product product) {
		super();
		this.vendor = vendor;
		this.product = product;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "VendorProductDTO [vendor=" + vendor + ", product=" + product + "]";
	}
	
}
