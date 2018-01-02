package com.pibicloud.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.pibicloud.model.DTTHeader;

public class DTTIteratorTest {
	private static Logger logger = Logger.getLogger(DTTIteratorTest.class);
	
	@Test
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
	
	@Test
	public void loadAndDisplayDTTStream() throws IOException {
		FileInputStream input = new FileInputStream("input/Lulu-DTT-00020014_20171127.DTT");
		Stream<DTTHeader> stream = StreamFactory.createDTTStream(input);
		
		stream.forEach(header -> logger.debug(String.format("%s", header.getFooter())));
	}
}