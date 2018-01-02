package com.pibicloud.io;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.pibicloud.io.model.Bill;
import com.pibicloud.io.model.BillItem;
import com.pibicloud.model.DTTDetail;
import com.pibicloud.model.DTTHeader;

public class StreamFactory {
	 
	
	public static Stream<DTTHeader> createDTTStream(InputStream input) throws IOException {
		DTTIterator iterator = new DTTIterator(input);
		Iterable<DTTHeader> iterable = () -> iterator;
		
		Stream<DTTHeader> stream = StreamSupport.stream(iterable.spliterator(), false);
		return stream;
	}
	
	public static boolean isBill(DTTHeader header) {
		
		if(header == null || header.getDTTDetails() == null || header.getDTTDetails().size()  == 0)
			return false;
		
		List<DTTDetail> details = header.getDTTDetails();
		
		return details.stream().anyMatch(item -> item.getFlag() == 'X');
	}
	
	
	private static ParsedBill createBill(DTTHeader header) { 
		ParsedBill bill = Parsers.parseBill(header); 
		List<DTTDetail> dTTDetails = header.getDTTDetails();
		List<BillItem> items = new LinkedList<BillItem>();
		
		for(DTTDetail detail: dTTDetails) {			
			char flag = detail.getFlag();
			
			if(flag == 'A' || flag == 'M' || flag == 'J' || flag == 'L') {				
				ParsedBillItem billItem = Parsers.parseBillItem(detail.getContent());
				
				if(billItem != null) {
					items.add(billItem); 
				}
				
			}
			
		}
		
		return bill;
	}
	
	public static Stream<Bill> createBillStream(InputStream input) throws IOException {
		Stream<DTTHeader> dttStream = createDTTStream(input);		
		return dttStream
				.filter(StreamFactory::isBill)
				.map(StreamFactory::createBill);
	}
}
