package com.pibicloud.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;


public class DTTHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8066452344766458491L;

	Matcher matcher;
	List<DTTDetail> dTTDetails;
	String footer;
	
	public DTTHeader(Matcher matcher, String footer, List<DTTDetail> dTTDetails) {
		super();
		this.matcher = matcher;
		this.footer = footer;
		this.dTTDetails = Collections.unmodifiableList(dTTDetails);
	}
	
	public List<DTTDetail> getDTTDetails() {
		return this.dTTDetails;
	}

	public String getFooter() {
		return footer;
	}
	
	public Matcher getMatcher() {
		return matcher;
	}
}
