package com.pibicloud.io;

import com.pibicloud.io.model.Bill;

public class ParsedBill extends Bill {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8985092171382179473L;

	String tableReference;
	String cashierReference;
	public String getTableReference() {
		return tableReference;
	}
	public void setTableReference(String tableReference) {
		this.tableReference = tableReference;
	}
	public String getCashierReference() {
		return cashierReference;
	}
	public void setCashierReference(String cashierReference) {
		this.cashierReference = cashierReference;
	}

	
}
