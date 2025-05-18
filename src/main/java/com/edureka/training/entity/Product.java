package com.edureka.training.entity;

//Product.java
public class Product {
 private long productid;
 private String productname;
 private String description;
 private int quantity;
 private double price;
private Vendor vendor;
 public Vendor getVendor() {
	return vendor;
}
public long getProductid() {
     return productid;
 }
 public void setProductid(long productid) {
     this.productid = productid;
 }
 public String getProductname() {
     return productname;
 }
 public void setProductname(String productname) {
     this.productname = productname;
 }
 public String getDescription() {
     return description;
 }
 public void setDescription(String description) {
     this.description = description;
 }
 public int getQuantity() {
     return quantity;
 }
 public void setQuantity(int quantity) {
     this.quantity = quantity;
 }
 public double getPrice() {
     return price;
 }
 public void setPrice(double price) {
     this.price = price;
 }
public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}


}
