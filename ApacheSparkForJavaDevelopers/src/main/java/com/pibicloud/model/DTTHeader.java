package com.pibicloud.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class DTTHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8066452344766458491L;

	List<DTTDetail> dTTDetails;
	String footer;
	
	public DTTHeader(String footer, List<com.pibicloud.model.DTTDetail> dTTDetails) {
		super();
		this.footer = footer;
		this.dTTDetails = Collections.unmodifiableList(dTTDetails);
	}
	
	public List<DTTDetail> getDTTDetails() {
		return this.dTTDetails;
	}

	public String getFooter() {
		return footer;
	}
	
	
}
