package com.pibicloud.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class StateMachine  {
	protected static Logger logger = Logger.getLogger(StateMachine.class);
	public static void execute(InputStream input, ProcessContextFactory contextFactory) {
		BufferedReader reader = new BufferedReader( new InputStreamReader(input));
		ProcessContext  context = contextFactory.apply();
		String line ;
		
		try {
			while((line = reader.readLine()) != null) {				
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	
}
