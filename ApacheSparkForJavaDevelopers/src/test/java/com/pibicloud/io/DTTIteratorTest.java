package com.pibicloud.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.pibicloud.io.model.Bill;
import com.pibicloud.model.DTTHeader;

public class DTTIteratorTest {
	private static Logger logger = Logger.getLogger(DTTIteratorTest.class);
	
//	@Test
	public void loadAndDisplayDTTIterator() throws IOException {
		FileInputStream input = new FileInputStream("input/Lulu-DTT-00020014_20171127.DTT");
		DTTIterator iterator = new DTTIterator(input);
		int count = 1;
		
		while(iterator.hasNext()) {
			DTTHeader header = iterator.next();
			logger.debug(String.format("#%d=%s", count,header.getFooter()));
			count++;
		}	
	}
	
//	@Test
	public void loadAndDisplayDTTStream() throws IOException {
		FileInputStream input = new FileInputStream("input/Lulu-DTT-00020014_20171127.DTT");
		Stream<DTTHeader> stream = StreamFactory.createDTTStream(input);
		
		stream.forEach(header -> logger.debug(String.format("%s", header.getFooter())));
	}
	
	@Test
	public void loadAndDisplayBillStream() throws IOException {
		FileInputStream input = new FileInputStream("input/Lulu-DTT-00020014_20171127.DTT");
		Stream<Bill> stream = StreamFactory.createBillStream(input);
		
		stream.forEach(
			bill -> logger.debug(String.format("id=%s, timestamp=%s, total=%12.2f", bill.getBillId(), Parsers.TIMESTAMP_FORMATTER.format(bill.getTimestamp()), bill.getTotal())));
	}
	
}