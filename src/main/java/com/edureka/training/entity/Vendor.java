package com.edureka.training.entity;

public class Vendor{
	Long vendorid;
	String vendorname;
	//String productname;
	String email;
	public Long getVendorid() {
		return vendorid;
	}
	public void setVendorid(Long vendorid) {
		this.vendorid = vendorid;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	Product product;
	String phone;
	String address ;
	//String description;

	


}
