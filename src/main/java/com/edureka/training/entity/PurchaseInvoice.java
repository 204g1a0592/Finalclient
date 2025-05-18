package com.edureka.training.entity;

import java.time.LocalDate;

public class PurchaseInvoice {
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Vendor vendor;
	

	    private int quantity;

	    public PurchaseInvoice() {
			//to-generated constructor stub
		}

		public PurchaseInvoice(long purchaseinvoiceid, Vendor vendor, int quantity, double Price, double totalCost,
				LocalDate date) {
			super();
			this.id = purchaseinvoiceid;
			this.vendor = vendor;
			this.quantity = quantity;
			this.price = Price;
			this.totalCost = totalCost;
			this.date = date;
		}

		public long getPurchaseinvoiceid() {
			return id;
		}

		public void setPurchaseinvoiceid(long purchaseinvoiceid) {
			this.id = purchaseinvoiceid;
		}

		public Vendor getVendor() {
			return vendor;
		}

		public void setVendor(Vendor vendor) {
			this.vendor = vendor;
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

		public void setPrice(double unitPrice) {
			this.price = unitPrice;
		}

		public double getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		private double price;

	    private double totalCost;

	    private LocalDate date;

}
