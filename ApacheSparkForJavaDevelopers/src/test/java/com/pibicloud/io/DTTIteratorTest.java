package com.pibicloud.io;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.pibicloud.model.DTTHeader;

public class DTTIteratorTest {
	private static Logger logger = Logger.getLogger(DTTIteratorTest.class);
	@Test
	public void loadAndDisplayDTT() throws IOException {
		FileInputStream input = new FileInputStream("input/Lulu-DTT-00020014_20171127.DTT");
		DTTIterator iterator = new DTTIterator(input);
		int count = 1;
		
		while(iterator.hasNext()) {
			DTTHeader header = iterator.next();
			logger.debug(String.format("#%d=%s", count,header.getFooter()));
			count++;
		}
		
		System.out.println(count);
	}
}