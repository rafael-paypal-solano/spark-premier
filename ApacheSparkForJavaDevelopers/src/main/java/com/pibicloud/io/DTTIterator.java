package com.pibicloud.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

import com.pibicloud.model.DTTDetail;
import com.pibicloud.model.DTTHeader;

public class DTTIterator implements Iterator<DTTHeader> {
	private static Logger logger = Logger.getLogger(DTTIterator.class);	
	private final static String UNEXPECTED_EOF_MSG="End of file reached.";
	private final static String END_OF_FILE_FLAG="FIN DE LECTURE";
	private final static Pattern PATTERN1 = Pattern.compile("^\\/\\d{2}-\\d{2}-\\d{4}\\s+\\d{2}:\\d{2}.+");
	private final static Pattern PATTERN2 = Pattern.compile(
			"\\s+<(\\d{2}-\\d{2}-\\d{2} \\d{2}:\\d{2})" // date time
			+ "(\\d{6})" // billid
			+ "/(.{1})" //bill edition
			+ "(.{11})" //price
			+ "\\s+"
			+ "(:.{2})" // seconds of the date
			+ ".{3}" // line count of the order
			+ "(.{5})" // 3 chars for number of operations on the table/room and 2 chars for number of the handy
			+ ".+");
	
	BufferedReader reader;
	DTTIteratorState state;
	DTTHeader lastRecord;
	String lastLine;
	
	public DTTIterator(InputStream input) throws IOException {
		reader = new BufferedReader(new InputStreamReader(input));
		state = DTTIteratorState.LOOKING_FOR_SALE_RECORDS;
		forward();		
	}
	
	private void moveToSalesRecordsSection() throws IOException {
		String line;
		
		while((line = reader.readLine()) != null && !line.startsWith("JOURNAL DES VENTES"));
		
		if(line == null) {
			state = DTTIteratorState.END_OF_FILE;
			return;
		}
		
		while((line = reader.readLine()) != null && PATTERN1.matcher(line).matches());
		
		if(line == null) {
			state = DTTIteratorState.END_OF_FILE;
			return;			
		}
		
		state = DTTIteratorState.IN_SALES_RECORDS_SECTION;
	 
	}
	
	private void readSaleRecord() throws IOException {
		String line;
		
		lastRecord = null;
		
		while(
			(line = reader.readLine()) != null && 
			(line = line.trim()).length() == 0 && 
			PATTERN1.matcher(line).matches()
		); //skips empty and nonrelevant lines
		
 		ArrayList<DTTDetail> details= new ArrayList<DTTDetail>();
 		while(
			(line = reader.readLine()) != null && 
			line.length() > 0 &&
			!line.startsWith("FIN DE LECTURE") &&
			!PATTERN2.matcher(line).matches()
		) {
			details.add(new DTTDetail(line.charAt(0), line));
 		}

		if(line == null)
			throw new IOException("Unexpected end of file");
 		
		if(line.startsWith(END_OF_FILE_FLAG)) {
 			state = DTTIteratorState.END_OF_FILE;
 			return;
 		}
 		
		lastRecord = new DTTHeader(line, details);
	}
	
	protected void forward() throws IOException {
		DTTIteratorState currentState = state;
		
		switch(currentState) {
			case LOOKING_FOR_SALE_RECORDS:
				moveToSalesRecordsSection();
				if(state == DTTIteratorState.IN_SALES_RECORDS_SECTION)
					readSaleRecord();
				break;
				
			case IN_SALES_RECORDS_SECTION:
				readSaleRecord();
				break;
				
			case END_OF_FILE:
				throw new IOException(UNEXPECTED_EOF_MSG);				
		}
	}
	
	@Override
	public boolean hasNext() {
		if(state == DTTIteratorState.END_OF_FILE)			
			return false;
		
		return true;
	}

	@Override
	public DTTHeader next() {
		DTTHeader record = lastRecord;
		try {
			readSaleRecord();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new NoSuchElementException(e.getMessage());
		}
		return record;
	}

}
