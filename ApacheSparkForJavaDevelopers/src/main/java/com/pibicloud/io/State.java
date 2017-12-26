package com.pibicloud.io;


public interface State{

	/**
	 * 
	 */
	
	public String getName();
	String process(ProcessContext context, String line);
}
