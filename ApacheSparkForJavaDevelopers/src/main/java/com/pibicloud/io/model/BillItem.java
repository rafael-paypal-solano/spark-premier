package com.pibicloud.io.model;


public class BillItem implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765070768725609933L;
	int line;
	double quantity;
	char status;
	double total;
	double discount;
	double price;
	
	public BillItem() { 
		
	}
	
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	} 

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
}
