package com.pibicloud.io.model;

import java.util.Date;
import java.util.List;

public class Bill implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6379344744483883892L;
	private long id;
	private char status;
	
	/* *
	private Table table;
	private Customer customer;
	private Cashier cashier; 
	private int pax;
	private CostCenter costCenter;
	private String ncf; //constructor
	/* */
	
	/* Mapped */ private double total;
	private double tax;
	private List<BillItem> items;
	private double discount;
	/* Mapped */ private String billId;
	/* Mapped */ private Date timestamp;
	private double tip;
	private double tipRate;
	private double taxRate;
	private boolean taxAndTipIncluded;
	private boolean fiscalValue;
	private double taxExcented;
	private double extraTip; 
	private double itemDiscount;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
	public double getTax() {
		return tax;
	}
	
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	public List<BillItem> getItems() {
		return items;
	}
	
	public void setItems(List<BillItem> items) {
		this.items = items;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getBillId() {
		return billId;
	}
	
	public void setBillId(String billId) {
		this.billId = billId;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public double getTip() {
		return tip;
	}
	
	public void setTip(double tip) {
		this.tip = tip;
	}
	
	public double getTipRate() {
		return tipRate;
	}
	public void setTipRate(double tipRate) {
		this.tipRate = tipRate;
	}
	
	public double getTaxRate() {
		return taxRate;
	}
	
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	
	public boolean isTaxAndTipIncluded() {
		return taxAndTipIncluded;
	}
	
	public void setTaxAndTipIncluded(boolean taxAndTipIncluded) {
		this.taxAndTipIncluded = taxAndTipIncluded;
	}
	
	public boolean isFiscalValue() {
		return fiscalValue;
	}
	
	public void setFiscalValue(boolean fiscalValue) {
		this.fiscalValue = fiscalValue;
	}
	
	public double getTaxExcented() {
		return taxExcented;
	}
	
	public void setTaxExcented(double taxExcented) {
		this.taxExcented = taxExcented;
	}
	
	public double getExtraTip() {
		return extraTip;
	}
	
	public void setExtraTip(double extraTip) {
		this.extraTip = extraTip;
	}
	
	public double getItemDiscount() {
		return itemDiscount;
	}
	public void setItemDiscount(double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}
	
	
}
