package com.pibicloud.io.model;


public class BillItem implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765070768725609933L;
	int line;
	double quantity;
	char status;
	Product product;
	
	
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


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	
}
