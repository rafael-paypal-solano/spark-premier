package com.pibicloud.model;

import java.io.Serializable;

public class DTTDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5769801863569484074L;

	char flag;
	
	String content;

	
	public DTTDetail(char flag, String content) {
		super();
		this.flag = flag;
		this.content = content;		
	}
	
	
	public char getFlag() {
		return flag;
	}

	public String getContent() {
		return content;
	}
	

	
}
