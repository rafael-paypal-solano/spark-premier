package com.pibicloud.io;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.pibicloud.model.DTTHeader;

public class StreamFactory {
	public static Stream<DTTHeader> createDTTStream(InputStream input) throws IOException {
		DTTIterator iterator = new DTTIterator(input);
		Iterable<DTTHeader> iterable = () -> iterator;
		
		Stream<DTTHeader> stream = StreamSupport.stream(iterable.spliterator(), false);
		return stream;
	}
}
