package com.pibicloud.io;

import com.pibicloud.io.model.BillItem;

public class ParsedBillItem extends BillItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264201801022615101L;

	String reference;
	String description;
	
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
